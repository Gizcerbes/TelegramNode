package com.uogames.telegram.bot.responses

import com.uogames.telegram.bot.responses.enums.ChatType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    @SerialName("id")
    val id: Long,
    @SerialName("type")
    val type: ChatType,
    @SerialName("title")
    val title: String? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("first_name")
    val firstName: String? = null,
    @SerialName("last_name")
    val lastName: String? = null,
    @SerialName("is_forum")
    val isForum: Boolean = false
)