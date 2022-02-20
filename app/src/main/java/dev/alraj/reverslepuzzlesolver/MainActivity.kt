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
import dev.alraj.reverslepuzzlesolver.ReverslePuzzle.Box.YELLOW
import dev.alraj.reverslepuzzlesolver.ReverslePuzzle.Box.GREEN
import dev.alraj.reverslepuzzlesolver.ReverslePuzzle.Box.GREY
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    private val lines = Array(4) { Array(5) { GREY } }
    private val boxInfi = infiOf(GREY, YELLOW, GREEN)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        Timber.plant(Timber.DebugTree())
        setupClicker()
        vb.find.setOnClickListener { find() }

        repeat(10) {
            Timber.d("%d %s", it, boxInfi.next().name)
        }
    }

    private fun find() {
        vb.lines.root.children.forEach { line ->
            (line as LinearLayout).forEach { box ->
                (box as TextView).text = ""
            }
        }

        if (vb.mainAnswer.text.isBlank()) {
            Toast.makeText(this, "What to find?", Toast.LENGTH_SHORT).show()
            return
        }
        if(vb.mainAnswer.text.toString().length != 5) {
            Toast.makeText(this, "Answer should be 5 characters only", Toast.LENGTH_SHORT).show()
            return
        }
        val answer = vb.mainAnswer.text.toString()

        val notFound = StringBuilder()
        lines.forEachIndexed { index, line ->
            val foundList = ReverslePuzzle.findAnswers(answer, line.toList(), words)
            if(foundList.isEmpty()) {
                notFound.append("No answers found for line ${index+1}\n")
                return@forEachIndexed
            }

            val lineLL = vb.lines.root.getChildAt(index) as LinearLayout
            foundList[0].forEachIndexed { characterIndex, character ->
                (lineLL.getChildAt(characterIndex) as TextView).text = character.toString()
            }
        }
        vb.foundAnswers.text = notFound.toString()
    }

    private fun setupClicker() {
        listOf(vb.lines.line1, vb.lines.line2, vb.lines.line3, vb.lines.line4)
            .forEachIndexed { lineIndex, line ->
                line.root.children
                    .forEachIndexed { boxIndex, box ->
                        box.setOnClickListener {
                            val currentBox = lines[lineIndex][boxIndex]
                            val newBox = boxInfi.with(currentBox).next()
                            box.setBackgroundResource(newBox.color)
                            lines[lineIndex][boxIndex] = newBox
                        }
                    }
            }
    }
}