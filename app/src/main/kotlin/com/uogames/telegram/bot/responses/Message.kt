package com.uogames.telegram.bot.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("message_id")
    val messageID: Int,
    @SerialName("message_thread_id")
    val messageThreadID: Int? = null,
    @SerialName("from")
    val from: User? = null,
    @SerialName("sender_chat")
    val senderChat: Chat? = null,
    @SerialName("sender_boost_count")
    val senderBoostCount: Int? = null,
    @SerialName("sender_business_bot")
    val senderBusinessBot: User? = null,
    @SerialName("date")
    val date: Int,
    @SerialName("business_connection_id")
    val businessConnectionID: String? = null,
    @SerialName("chat")
    val chat: Chat,
    @SerialName("forward_origin")
    val forwardOrigin: MessageOrigin? = null,
    @SerialName("is_topic_message")
    val isTopicMessage: Boolean = false,
    @SerialName("is_automatic_forward")
    val isAutomaticForward: Boolean = false,
    @SerialName("reply_to_message")
    val replyToMessage: Message? = null,



    @SerialName("text")
    val text: String?,
    @SerialName("entities")
    val entities: List<MessageEntity> = emptyList()
)