package com.bontech.kmp

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hellouuu, ${platform.name}!"
    }
}

