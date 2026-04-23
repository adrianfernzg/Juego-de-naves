package com.example.juegonaves.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

class PlayerShip(var x: Float, var y: Float, private val screenHeight: Int) {
    val size = 60f

    fun moveTo(targetY: Float) {
        y = targetY.coerceIn(size, screenHeight - size)
    }

    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL

        val path = Path()
        path.moveTo(x + size, y)
        path.lineTo(x - size, y - size)
        path.lineTo(x - size, y + size)
        path.close()

        canvas.drawPath(path, paint)

        paint.color = Color.YELLOW
        canvas.drawCircle(x, y, size / 3, paint)
    }
}
