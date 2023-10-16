package dev.alraj.reverslepuzzlesolver

import dev.alraj.reverslepuzzlesolver.shared.Box.*
import dev.alraj.reverslepuzzlesolver.shared.ReversleSolver
import org.junit.Test
import org.junit.Assert.assertEquals

class ReversleSolverTest {
    @Test
    fun correct() {
        val input = "WISPY"
        val words = dev.alraj.reverslepuzzlesolver.shared.WORDS
        val box = listOf(
            listOf(YELLOW, GREY, GREY, GREY, GREY),
            listOf(YELLOW, GREEN, GREY, GREY, GREY),
            listOf(GREY, YELLOW, YELLOW, GREY, GREY),
            listOf(GREEN, GREY, GREY, GREY, GREEN),
        )

        val output = box.map { ReversleSolver.findAnswers(input, it, words) }
        val expected = listOf("PLACE", "SINCE", "USING", "WORRY")
        assertEquals(expected, output)
    }
}