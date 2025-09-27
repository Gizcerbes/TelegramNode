package com.uogames.telegram.bot.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPhoto(
    @SerialName("small_file_id")
    val smallFileID: String,
    @SerialName("small_file_unique_id")
    val smallFileUniqueID: String,
    @SerialName("big_file_id")
    val bigFileID: String,
    @SerialName("big_file_unique_id")
    val bigFileUniqueID: String
)