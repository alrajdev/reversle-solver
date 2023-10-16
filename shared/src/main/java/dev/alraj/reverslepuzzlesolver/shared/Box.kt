package dev.alraj.reverslepuzzlesolver.shared

import androidx.annotation.ColorRes

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