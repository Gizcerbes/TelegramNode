package com.uogames.telegram.bot.responses

import com.uogames.telegram.bot.responses.enums.MessageOriginType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageOrigin(
    @SerialName("type")
    val type: MessageOriginType,
    @SerialName("date")
    val date: Int, // unix time
    @SerialName("sender_user")
    val senderUser: User? = null, // type USER
    @SerialName("sender_user_name")
    val senderUserName: String? = null, // type HIDDEN_USER
    @SerialName("sender_chat")
    val senderChat: Chat? = null, // type CHAT
    @SerialName("author_signature")
    val authorSignature: String? = null, // type CHAT or CHANNEL
    @SerialName("chat")
    val chat: Chat? = null, // type CHANNEL
    @SerialName("message_id")
    val messageID: Int? = null, // type CHANNEL
)