package com.example.savethebird.models

import com.example.savethebird.logics.RABBIT_WIDTH

class Rabbit {
    private var leftX: Int = 0
    private var rightX: Int = 0

    fun setLeftX(leftX: Int) {
        this.leftX = leftX
        this.rightX = leftX + RABBIT_WIDTH
    }

    fun getRightX(): Int {
        return rightX
    }

    fun getLeftX(): Int {
        return leftX
    }

}