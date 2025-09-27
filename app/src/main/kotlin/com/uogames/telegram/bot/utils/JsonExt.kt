package com.uogames.telegram.bot.utils

import kotlinx.serialization.json.Json

object JsonExt {

    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = false
        classDiscriminator = "qwerty"
    }

}