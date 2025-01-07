package com.example.savethebird.logics

import com.example.savethebird.models.Carrot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarrotLogic {
    private val _createCarrot = MutableStateFlow(Carrot(linePosition = getRandomLine()))
    val createCarrot: StateFlow<Carrot> = _createCarrot

    private fun getRandomLine(): Int {
        return (0..2).random()
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(CARROT_CREATION_DELAY)
                _createCarrot.value = Carrot(linePosition = getRandomLine())
            }
        }
    }

}