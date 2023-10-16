package dev.alraj.reverslepuzzlesolver.shared

object ReversleSolver {
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
}