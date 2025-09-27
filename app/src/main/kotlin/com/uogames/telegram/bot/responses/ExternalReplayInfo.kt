package com.uogames.telegram.bot.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalReplayInfo(
    @SerialName("origin")
    val origin: MessageOrigin,
    @SerialName("chat")
    val chat: Chat? = null,
    @SerialName("message_id")
    val messageID: Long? = null,
    @SerialName("link_preview_options")
    val linkPreviewOptions: LinkPreviewOptions? = null,
   // val animation: Ani
)