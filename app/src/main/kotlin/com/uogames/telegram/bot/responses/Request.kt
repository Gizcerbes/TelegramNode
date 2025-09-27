package com.uogames.telegram.bot.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Request<T>(
    @SerialName("ok")
    val ok: Boolean,
    @SerialName("result")
    val result: T,
)