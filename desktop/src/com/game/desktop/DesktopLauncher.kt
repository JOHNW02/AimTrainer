package com.game.desktop

import com.badlogic.gdx.Application
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.game.AimTrainer

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration().apply {
            title = "AimTrainer"
            width = 800
            height = 480
        }
        LwjglApplication(AimTrainer(), config).logLevel = Application.LOG_DEBUG
    }
}