package com.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.TimeUtils
import com.game.AimTrainer
import ktx.app.KtxScreen

class GameScreen(val game: AimTrainer) : KtxScreen {
    // load the images for the droplet & bucket, 64x64 pixels each
    private val redDot = Texture(Gdx.files.internal("C:/Users/John/IdeaProjects/AimTrainer/core/src/com/game/assets/images/red_dot.png"))
    // load the drop sound effect and the rain background music

    // The camera ensures we can render using our target resolution of 800x480
    private val camera = OrthographicCamera().apply { setToOrtho(false, 800f, 480f) }

    // create a Rectangle to logically represent the bucket
    // center the bucket horizontally
    // bottom left bucket corner is 20px above
    private val cursorlocation = Vector3()
    private val dotlocation = Vector3()


    // create the raindrops array and spawn the first raindrop
    private var lastDropTime = 0L
    private var score = 0

    private fun moveDot() {
        MathUtils.random(0f, 800f - 64f)
        dotlocation.x = MathUtils.random(0f, 800f - 64f)
        dotlocation.y = MathUtils.random(0f, 480f - 64f)
        dotlocation.z = 0f
        lastDropTime = TimeUtils.nanoTime()
    }

    override fun render(delta: Float) {
        // generally good practice to update the camera's matrices once per frame
        camera.update()
        game.batch.projectionMatrix = camera.combined

        val cursor = Gdx.graphics.newCursor(Pixmap(Gdx.files.internal("C:/Users/John/IdeaProjects/AimTrainer/core/src/com/game/assets/cursor.png")), 32, 32)
        Gdx.graphics.setCursor(cursor)
        cursorlocation.x = Gdx.input.getX(0).toFloat()
        cursorlocation.y = (Gdx.input.getY(0)).toFloat()
        cursorlocation.z = 0f
        camera.unproject(cursorlocation)
        // begin a new batch and draw the bucket and all drops
        game.batch.begin()
        game.font.draw(game.batch, "Score: $score", 0f, 480f)
        game.batch.draw(redDot, dotlocation.x, dotlocation.y, 10f, 10f)
        game.batch.end()

        if (Math.abs(cursorlocation.x - dotlocation.x) < 8 && Math.abs(cursorlocation.y - dotlocation.y) < 8) {
            moveDot()
            score++
        }
    }

    override fun show() {

    }

    override fun dispose() {
    }
}