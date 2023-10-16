package dev.alraj.reverslepuzzlesolver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.forEach
import dev.alraj.infi.infiOf
import dev.alraj.reverslepuzzlesolver.databinding.ActivityMainBinding
import dev.alraj.reverslepuzzlesolver.shared.Box.*
import dev.alraj.reverslepuzzlesolver.shared.ReversleSolver
import dev.alraj.reverslepuzzlesolver.shared.WORDS
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val boxGrid = Array(4) { Array(5) { GREY } }
    private val colors = infiOf(GREY, YELLOW, GREEN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        setupClicker()
        binding.find.setOnClickListener { find() }

        repeat(10) {
            Timber.d("%d %s", it, colors.next().name)
        }
    }

    private fun find() {
        binding.boxGrid.root.children.forEach { line ->
            (line as LinearLayout).forEach { box ->
                (box as TextView).text = ""
            }
        }

        if (binding.mainAnswer.text.isBlank()) {
            Toast.makeText(this, "What to find?", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.mainAnswer.text.toString().length != 5) {
            Toast.makeText(this, "Answer should be 5 characters only", Toast.LENGTH_SHORT).show()
            return
        }
        val answer = binding.mainAnswer.text.toString()

        val notFound = StringBuilder()
        boxGrid.forEachIndexed { index, boxRow ->
            val foundString = ReversleSolver.findAnswers(
                answer,
                boxRow.toList(),
                WORDS
            )
            if (foundString == null) {
                notFound.append("No answers found for line ${index + 1}\n")
                return@forEachIndexed
            }

            val lineLL = binding.boxGrid.root.getChildAt(index) as LinearLayout
            foundString.forEachIndexed { characterIndex, character ->
                (lineLL.getChildAt(characterIndex) as TextView).text = character.toString()
            }
        }
        binding.foundAnswers.text = notFound.toString()
    }

    private fun setupClicker() {
        listOf(
            binding.boxGrid.row1,
            binding.boxGrid.row2,
            binding.boxGrid.row3,
            binding.boxGrid.row4
        )
            .forEachIndexed { lineIndex, row ->
                row.root.children
                    .forEachIndexed { boxIndex, box ->
                        box.setOnClickListener {
                            val currentBox = boxGrid[lineIndex][boxIndex]
                            val newBox = colors.with(currentBox).next()
                            box.setBackgroundResource(newBox.color)
                            boxGrid[lineIndex][boxIndex] = newBox
                        }
                    }
            }
    }
}