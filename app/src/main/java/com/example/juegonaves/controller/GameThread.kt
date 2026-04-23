package com.example.juegonaves.controller

import android.graphics.Canvas
import android.view.SurfaceHolder
import com.example.juegonaves.view.GameView

class GameThread(private val surfaceHolder: SurfaceHolder, private val gameView: GameView) : Thread() {

    private var running = false
    private val targetFPS = 60
    private val targetTime = (1000 / targetFPS).toLong()

    fun setRunning(isRunning: Boolean) {
        running = isRunning
    }

    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        var canvas: Canvas?

        while (running) {
            startTime = System.currentTimeMillis()
            canvas = null

            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    gameView.update()
                    canvas?.let { gameView.draw(it) }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = System.currentTimeMillis() - startTime
            waitTime = targetTime - timeMillis

            try {
                if (waitTime > 0) {
                    sleep(waitTime)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
