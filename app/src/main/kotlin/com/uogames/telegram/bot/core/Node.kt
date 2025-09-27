package com.uogames.telegram.bot.core

import com.uogames.telegram.bot.BotClient
import com.uogames.telegram.bot.responses.Update

data class Node(
    val chatID: Long,
    var command: String,
    var position: Int,
    var update: Update,
    val bot: BotClient
) {

    fun endNode(){
        val node = this
        bot.apply { endNode(update, node) }
    }


}
