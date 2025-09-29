package com.uogames.telegram.bot.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Document(
    @SerialName("file_name") val filename: String,
    @SerialName("file_id") val fileId: String,
    @SerialName("file_unique_id") val fileUniqueId: String
)