package com.game

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.screen.MainMenuScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class AimTrainer : KtxGame<KtxScreen>() {
    val batch by lazy { SpriteBatch() }

    // use LibGDX's default Arial font
    val font by lazy { BitmapFont() }

    override fun create() {
        addScreen(MainMenuScreen(this))
        setScreen<MainMenuScreen>()
        super.create()
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        super.dispose()
    }
}