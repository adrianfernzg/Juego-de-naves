package com.example.juegonaves.model

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

class EnemyShip(var x: Float, var y: Float) {
     val size = 50f

    fun update(speed: Float) {
        x += speed
    }

    fun draw(canvas: Canvas, paint: Paint) {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL

        val path = Path()
        path.moveTo(x - size, y)
        path.lineTo(x + size, y - size)
        path.lineTo(x + size, y + size)
        path.close()

        canvas.drawPath(path, paint)

        paint.color = Color.MAGENTA
        canvas.drawCircle(x, y, size / 3, paint)
    }
}
