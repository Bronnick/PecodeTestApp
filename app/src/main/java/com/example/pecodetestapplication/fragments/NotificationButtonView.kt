package com.example.pecodetestapplication.fragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class NotificationButtonView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.parseColor("#33BABE")
        paint.style = Paint.Style.FILL

        textPaint.color = Color.WHITE
        textPaint.textSize = 30f
        textPaint.textAlign = Paint.Align.CENTER

        canvas.drawCircle(width / 2f, height / 2f, 250f, paint)
        canvas.drawText("Create new\r\nnotification", width / 2f, height / 2f, textPaint)
    }
}