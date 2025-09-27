package com.uogames.telegram.bot.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinkPreviewOptions(
    @SerialName("is_disabled")
    val isDisabled: Boolean? = false,
    @SerialName("url")
    val url: String? = null,
    @SerialName("prefer_small_media")
    val preferSmallMedia: Boolean? = null,
    @SerialName("prefer_large_media")
    val preferLargeMedia: Boolean? = null,
    @SerialName("show_above_text")
    val showAboveText: Boolean? = null
)