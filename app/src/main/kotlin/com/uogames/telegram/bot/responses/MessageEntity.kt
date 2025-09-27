package com.uogames.telegram.bot.responses

import com.uogames.telegram.bot.responses.enums.MessageEntityType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageEntity(
    @SerialName("type")
    val type: MessageEntityType,
    @SerialName("offset")
    val offset: Int,
    @SerialName("length")
    val length: Int,
    @SerialName("url")
    val url: String? = null,
    @SerialName("user")
    val user: User? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("custom_emoji_id")
    val customEmojiID: String? = null
)