package com.uogames.telegram.bot

import com.uogames.telegram.bot.core.Node
import com.uogames.telegram.bot.responses.Request
import com.uogames.telegram.bot.responses.Update
import com.uogames.telegram.bot.utils.JsonExt
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TelegramBot(
    private val botID: String,
    private val scope: CoroutineScope
) : BotClient {

    private var latestID: Long = 0
    private val chains = HashMap<Long, Node>()


    private val client = HttpClient(CIO) {
        install(ContentNegotiation) { json(json = JsonExt.json) }
        install(DefaultRequest) {
            url("https://api.telegram.org/$botID/")
        }
        install(HttpRequestRetry) {
            maxRetries = 2
        }
    }

    override fun start(
        isStart: () -> Boolean,
        looper: (suspend BotClient.() -> Unit)?,
        receiver: suspend BotClient.(Update) -> Unit
    ) {
        scope.launch {
            while (isStart()) {
                runCatching {
                    val r = client.get("getUpdates") { parameter("offset", latestID + 1) }.body<Request<List<Update>>>()
                    scope.launch { looper?.let { runCatching { it() }.onFailure { println(it) } } }
                    r.result.forEach { scope.launch { runCatching { receiver(it) }.onFailure { println(it) } } }
                    latestID = r.result.lastOrNull()?.updateID ?: latestID
                    delay(1_000)
                }.onFailure {
                    println("Bot Exception $it")
                    delay(1_000)
                }

            }
        }
    }

    override suspend fun BotClient.respond(update: Update?, url: String, block: HttpRequestBuilder.() -> Unit): HttpResponse {
        setLatestID(update?.updateID ?: 0)
        return client.post(url, block)
    }

    override suspend fun BotClient.node(update: Update, command: String, box: suspend Node.() -> Unit) {
        val chatID = update.message?.chat?.id ?: return
        val text = update.message.text?.split(" ")?.get(0)
        if (text == command) chains[chatID] = Node(chatID, command, 0, update, this)
        val c = chains[chatID] ?: return
        if (c.command != command) return
        if (text.orEmpty().startsWith("/") && text != command) return
        c.update = update
        c.box()
        latestID = update.updateID
    }

    override fun BotClient.endNode(update: Update, chain: Node) {
        chains.remove(chain.chatID)
        latestID = update.updateID
    }


    @Synchronized
    private fun setLatestID(id: Long) {
        if (latestID < id) latestID = id
    }


}

interface BotClient {

    fun start(
        isStart: () -> Boolean,
        looper: (suspend BotClient.() -> Unit)? = null,
        receiver: suspend BotClient.(Update) -> Unit
    )

    suspend fun BotClient.respond(
        update: Update? = null,
        url: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse

    suspend fun BotClient.node(update: Update, command: String, box: suspend Node.() -> Unit)


    fun BotClient.endNode(update: Update, chain: Node)

}

