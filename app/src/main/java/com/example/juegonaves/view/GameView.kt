package com.example.juegonaves.view

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.juegonaves.R
import com.example.juegonaves.controller.GameActivity
import com.example.juegonaves.controller.GameOverActivity
import com.example.juegonaves.controller.GameThread
import com.example.juegonaves.model.EnemyShip
import com.example.juegonaves.model.PlayerShip
import kotlin.random.Random

class GameView(context: Context, private val nombreJugador: String, private val dificultad: String) :
    SurfaceView(context), SurfaceHolder.Callback {

    private var thread: GameThread? = null
    private val paint = Paint()
    private var playerShip: PlayerShip? = null
    private val enemyShips = mutableListOf<EnemyShip>()
    private var spawnTimer = 0L
    private var spawnInterval = 2000L
    private var maxEnemies = 3
    private var enemySpeed = 5f
    private var lastSpeedIncrease = 0L
    private var backgroundMusic: MediaPlayer? = null
    private var collisionSound: MediaPlayer? = null
    private var isGameOver = false
    private var score = 0

    init {
        holder.addCallback(this)
        when (dificultad) {
            "Fácil" -> {
                maxEnemies = 2
                enemySpeed = 4f
                spawnInterval = 3000L
            }
            "Medio" -> {
                maxEnemies = 3
                enemySpeed = 5f
                spawnInterval = 2000L
            }
            "Difícil" -> {
                maxEnemies = 5
                enemySpeed = 6f
                spawnInterval = 1500L
            }
        }
        try {
            backgroundMusic = MediaPlayer.create(context, R.raw.background_music)
            backgroundMusic?.isLooping = true
            collisionSound = MediaPlayer.create(context, R.raw.collision_sound)
        } catch (e: Exception) {
            // Silently fail if resources don't exist yet
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        playerShip = PlayerShip(width - 150f, height / 2f, height)
        thread = GameThread(holder, this)
        thread?.setRunning(true)
        thread?.start()
        backgroundMusic?.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        thread?.setRunning(false)
        while (retry) {
            try {
                thread?.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        backgroundMusic?.release()
        collisionSound?.release()
    }

    fun update() {
        if (isGameOver) return
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastSpeedIncrease > 10000L) {
            enemySpeed += 0.5f
            lastSpeedIncrease = currentTime
        }

        if (currentTime - spawnTimer > spawnInterval && enemyShips.size < maxEnemies) {
            enemyShips.add(EnemyShip(-100f, Random.nextFloat() * height))
            spawnTimer = currentTime
        }

        val iterator = enemyShips.iterator()
        while (iterator.hasNext()) {
            val enemy = iterator.next()
            enemy.update(enemySpeed)

            if (enemy.x > width) {
                iterator.remove()
                score++
            }

            playerShip?.let { player ->
                if (checkCollision(player, enemy)) {
                    gameOver()
                }
            }
        }
    }

    private fun checkCollision(player: PlayerShip, enemy: EnemyShip): Boolean {
        val dx = player.x - enemy.x
        val dy = player.y - enemy.y
        val distance = Math.sqrt((dx * dx + dy * dy).toDouble())
        return distance < (player.size + enemy.size) * 0.7 // Colisión aproximada
    }

    private fun gameOver() {
        isGameOver = true
        backgroundMusic?.stop()
        collisionSound?.start()
        post {
            val intent = Intent(context, GameOverActivity::class.java)
            intent.putExtra("NOMBRE_JUGADOR", nombreJugador)
            intent.putExtra("SCORE", score)
            intent.putExtra("DIFICULTAD", dificultad)
            context.startActivity(intent)
            (context as GameActivity).finish()
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(Color.BLACK)
        playerShip?.draw(canvas, paint)
        for (enemy in enemyShips) {
            enemy.draw(canvas, paint)
        }
        paint.color = Color.WHITE
        paint.textSize = 50f
        canvas.drawText("Puntos: $score", 50f, 80f, paint)
        canvas.drawText("Jugador: $nombreJugador", 50f, 140f, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isGameOver) {
            playerShip?.moveTo(event.y)
        }
        return true
    }

    fun pause() {
        backgroundMusic?.pause()
    }

    fun resume() {
        if (!isGameOver) {
            backgroundMusic?.start()
        }
    }
}
