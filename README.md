## Example
```kotlin
    val botID = "bot0000000000:AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    val workScope = CoroutineScope(Dispatchers.IO)
    val bot = TelegramBot(botID, workScope)

    bot.start(
       isStart = { true }
    ) { update ->
        node(update, "/start") {
            when (position) {
                0 -> {
                    sendClearMessage("Hello. What is your name?")
                    position = 1
                }

                1 -> {
                    val text = this.update.message?.text
                    if (text != null) {
                        sendClearMessage("Hello $text")
                        sendClearMessage("There are commands here /mirror and /stop")
                        this.endNode()
                    }
                }

                else -> this.endNode()
            }
        }
        node(update, "/mirror") {
            when (position) {
                0 -> {
                    sendClearMessage("Now everything you write should come back.")
                    position = 1
                }

                1 -> {
                    sendClearMessage(update.message?.text ?: "There was something wrong")
                }
            }
        }

        node(update, "/stop") {
            sendClearMessage("Now all commands are cancelled")
            this.endNode()
        }
    }
```