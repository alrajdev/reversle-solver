package dev.alraj.reverslepuzzlesolver

import org.junit.Test
import dev.alraj.reverslepuzzlesolver.ReverslePuzzle.Box.*
import org.junit.Assert.assertEquals

class ReversleSolverTest {
    @Test
    fun correct() {
        val input = "WISPY"
        val words = words
        val box = listOf(
            listOf(YELLOW, GREY, GREY, GREY, GREY),
            listOf(YELLOW, GREEN, GREY, GREY, GREY),
            listOf(GREY, YELLOW, YELLOW, GREY, GREY),
            listOf(GREEN, GREY, GREY, GREY, GREEN),
        )

        val output = box.map { ReverslePuzzle.findAnswers(input, it, words) }
        val expected = listOf("PLACE", "SINCE", "USING", "WORRY")
        assertEquals(expected, output)
    }
}