package dev.alraj.reverslepuzzlesolver

import androidx.annotation.ColorRes

object ReverslePuzzle {
    fun findAnswers(answer: String, boxRow: List<Box>, words: List<String>): String? {
        return answer.uppercase().let { answer ->
            words.firstOrNull { word -> isWordMatch(answer, boxRow, word) }
        }

    }

    private fun isWordMatch(answer: String, boxRow: List<Box>, word: String, ): Boolean {
        boxRow.forEachIndexed { index, box ->
            when(box) {
                Box.GREEN -> {
                    if(word[index] != answer[index]) return false
                }
                Box.YELLOW -> {
                    if(word[index] == answer[index] || word[index] !in answer )
                        return false
                }
                Box.GREY -> {
                    if(word[index] in answer) return false
                }
            }
        }
        return true
    }

    enum class Box(@ColorRes val color: Int) {
        /**
         * You have to use the same letter as in the solution word at the same positions
         */
        GREEN(R.color.green),

        /**
         * You have to use letters which are different from the solution word at the same position, but appear in the solution word
         */
        YELLOW(R.color.yellow),

        /**
         * You have to use letters which don't appear in the solution word
         */
        GREY(R.color.grey)
    }
}