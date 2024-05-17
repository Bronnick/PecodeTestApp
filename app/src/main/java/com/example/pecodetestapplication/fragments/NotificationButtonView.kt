package com.example.pecodetestapplication.fragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation

fun StaticLayout.draw(canvas: Canvas, x: Float, y: Float) {
    canvas.withTranslation(x, y) {
        draw(this)
    }
}

class NotificationButtonView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.parseColor("#33BABE")
        paint.style = Paint.Style.FILL

        textPaint.color = Color.WHITE
        textPaint.textSize = 60f
        textPaint.textAlign = Paint.Align.CENTER

        canvas.drawCircle(width / 2f, height / 2f, 250f, paint)

        val text = "Create new\n notification"
        val staticLayout = StaticLayout.Builder
            .obtain("Create new\n notification", 0,  text.length, textPaint, width)
            .build()

        staticLayout.draw(canvas, width / 2f, height / 2f - 80f)
        //canvas.drawText("Create new\n notification", width / 2f, height / 2f, textPaint)
    }
}