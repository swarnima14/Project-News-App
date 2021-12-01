package com.app.appnews

object ColorPicker {
    val colors = arrayOf("#ffffff", "#A34244")
    var colorIndex = 0
    fun getColor(): String{
        return colors[colorIndex++ % colors.size]
    }
}