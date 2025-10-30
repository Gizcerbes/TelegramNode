package com.uogames.telegram.bot.utils

import kotlinx.serialization.json.Json

internal object JsonExt {

    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = false
    }

}