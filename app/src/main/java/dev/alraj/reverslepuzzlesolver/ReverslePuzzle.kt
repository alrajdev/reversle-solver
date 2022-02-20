package dev.alraj.reverslepuzzlesolver

import androidx.annotation.ColorRes
import timber.log.Timber
import timber.log.Timber.Forest.d

object ReverslePuzzle {
    fun findAnswers(answer: String, line: List<Box>, words: List<String>): List<String> {
        val mainAnswer = answer.uppercase()
        val foundAnswers = mutableListOf<String>()
        words.forEach { word ->
            line.forEachIndexed { index, box ->
                when(box) {
                    Box.GREEN -> {
                        if(word[index] != mainAnswer[index]) return@forEach
                    }
                    Box.YELLOW -> {
                        if(word[index] == mainAnswer[index] || word[index] !in mainAnswer )
                            return@forEach
                    }
                    Box.GREY -> {
                        if(word[index] in mainAnswer) return@forEach
                    }
                }
                if(index == word.lastIndex) foundAnswers.add(word)
            }
        }

        return foundAnswers
    }

    enum class Box(@ColorRes val color: Int) {
        GREEN(R.color.green), YELLOW(R.color.yellow), GREY(R.color.grey)
    }
}