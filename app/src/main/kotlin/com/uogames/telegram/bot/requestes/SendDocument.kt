package com.uogames.telegram.bot.requestes

import com.uogames.telegram.bot.BotClient
import com.uogames.telegram.bot.core.Node
import com.uogames.telegram.bot.responses.Message
import com.uogames.telegram.bot.responses.Request
import com.uogames.telegram.bot.responses.Update
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object SendDocument {

    private const val METHOD = "sendDocument"

    suspend fun BotClient.sendDocument(
        update: Update? = null,
        file: ByteArray,
        filename: String,
    ): Request<Message> {
        return respond(update, METHOD) {
            contentType(ContentType.MultiPart.FormData)
            setBody(MultiPartFormDataContent(formData {
                update?.message?.chat?.id?.let { append("chat_id", it) }
                append("document", file, Headers.build {
                    append(HttpHeaders.ContentType, ContentType.Image.Any.toString())
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
            }))
        }.body()
    }


    suspend fun BotClient.sendDocument(
        chatID: Long,
        file: ByteArray,
        filename: String,
        update: Update? = null
    ): Request<Message> {
        return respond(update, METHOD) {
            contentType(ContentType.MultiPart.FormData)
            setBody(MultiPartFormDataContent(formData {
                append("chat_id", chatID)
                append("document", file, Headers.build {
                    append(HttpHeaders.ContentType, ContentType.Image.Any.toString())
                    append(HttpHeaders.ContentDisposition, "filename=\"$filename\"")
                })
            }))
        }.body()
    }

    suspend fun BotClient.sendDocument(
        chatID: Long,
        fileID: String,
        update: Update? = null
    ): Request<Message> {
        return respond(update, METHOD) {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("chat_id", chatID)
                put("document", fileID)
            })
        }.body()
    }

    suspend fun Node.sendDocument(
        file: ByteArray,
        filename: String,
    ): Request<Message> {
        return bot.sendDocument(update, file, filename)
    }

    suspend fun Node.sendDocument(
        fileID: String,
    ): Request<Message> {
        return this.bot.sendDocument(this.chatID, fileID, update)
    }


}