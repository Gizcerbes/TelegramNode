package com.uogames.telegram.bot.requestes

import com.uogames.telegram.bot.BotClient
import com.uogames.telegram.bot.core.Node
import com.uogames.telegram.bot.requestes.SendMessage.sendMessage
import com.uogames.telegram.bot.responses.Message
import com.uogames.telegram.bot.responses.Request
import com.uogames.telegram.bot.responses.Update
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object SendMessage {

    private const val METHOD = "sendMessage"


    suspend fun BotClient.sendMessage(update: Update? = null, message: String): Request<Message> {
        return respond(update, METHOD) {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("chat_id",  update?.message?.chat?.id)
                put("text", message)
                put("parse_mode", "MarkdownV2")
            })
        }.apply {
            if (this.status != HttpStatusCode.OK) {
                println(this)
                println(this.bodyAsText())
            }
        }.body()
    }

    suspend fun BotClient.sendMessage(chatID: Long, message: String): Request<Message> {
        return respond(null, METHOD) {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("chat_id",  chatID)
                put("text", message)
            })
        }.body()
    }

    suspend fun Node.sendMessage(message: String): Request<Message> {
        return bot.sendMessage(update, message).apply {
          //  println(message)
        }
    }

    suspend fun Node.sendClearMessage(message: String): Request<Message>{
        return bot.sendMessage(update, message.clearMessage())
    }

    fun String.clearMessage(): String{
        return replace(".", "\\.")
            .replace("_", "\\_")
            .replace("*", "\\*")
            .replace("[", "\\[")
            .replace("]", "\\]")
            .replace("(", "\\(")
            .replace(")", "\\)")
            .replace("~", "\\~")
            .replace("`", "\\`")
            .replace(">", "\\>")
            .replace("#", "\\#")
            .replace("+", "\\+")
            .replace("-", "\\-")
            .replace("=", "\\=")
            .replace("|", "\\|")
            .replace("{", "\\{")
            .replace("}", "\\}")
            .replace("!", "\\!")
    }


}