package com.ing3.calculatrice

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    private lateinit var textExpression: TextView
    private lateinit var textResult: TextView
    private lateinit var buttonEqual: AppCompatButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des TextViews
        textExpression = findViewById(R.id.text_expression)
        textResult = findViewById(R.id.text_result)

        // Initialisation du bouton "="
        buttonEqual = findViewById(R.id.button_equal)

        // Afficher 0 par défaut
        resetExpressionAndResult()

        // Gestion des boutons
        setupButtons()
    }
    private var justEvaluated: Boolean = false


    private fun setupButtons() {
        // Bouton "Clear"
        findViewById<AppCompatButton>(R.id.button_clear).setOnClickListener {
            resetExpressionAndResult()
        }

        // Bouton "DEL"
        findViewById<AppCompatButton>(R.id.button_del).setOnClickListener {
            val currentExpression = textExpression.text.toString()
            if (currentExpression.isNotEmpty()) {
                val updatedExpression = currentExpression.dropLast(1)
                textExpression.text = if (updatedExpression.isEmpty()) "0" else updatedExpression
            }
        }

        // Gestion des chiffres et opérateurs
        setupNumberButtons()
        setupOperatorButtons()

        // Boutons parenthèses
        setupParenthesisButtons()

        // Bouton "."
        setupDotButton()

        // Bouton "√"
        setupSqrtButton()


        // Bouton "="
        buttonEqual.setOnClickListener {
            textExpression.textSize = 30f
            textResult.textSize = 50f

            if (textExpression.text.isEmpty()) {
                textResult.text = "0"
            } else {
                val result = Calculator.evaluateExpression(
                    textExpression.text.toString()
                )
                textResult.text = result

                if (isError(result)) {
                    textResult.textSize = 20f
                    textResult.setTextColor(resources.getColor(android.R.color.holo_red_light))
                } else {
                    textResult.textSize = 50f
                }
                justEvaluated = true // Active le flag après évaluation
            }
        }
    }

    private fun setupNumberButtons() {
        val numbers = listOf(
            R.id.button_0 to "0", R.id.button_1 to "1", R.id.button_2 to "2",
            R.id.button_3 to "3", R.id.button_4 to "4", R.id.button_5 to "5",
            R.id.button_6 to "6", R.id.button_7 to "7", R.id.button_8 to "8", R.id.button_9 to "9"
        )

        for ((id, value) in numbers) {
            findViewById<AppCompatButton>(id).setOnClickListener {
                if (justEvaluated) {
                    textExpression.text = value
                    justEvaluated = false
                } else {
                    if (textExpression.text.toString() == "0") {
                        textExpression.text = value
                    } else {
                        textExpression.append(value)
                    }
                }
            }
        }
    }

    private fun setupOperatorButtons() {
        val operators = listOf(
            R.id.button_add to "+", R.id.button_subtract to "-",
            R.id.button_multiply to "×", R.id.button_divide to "÷",
            R.id.button_modulo to " mod ", R.id.button_exposant to "^"
        )

        for ((id, value) in operators) {
            findViewById<AppCompatButton>(id).setOnClickListener {
                if (justEvaluated) {
                    justEvaluated = false // Réinitialise le flag
                }
                val currentExpression = textExpression.text.toString()
                if (currentExpression.isNotEmpty()) {
                    val lastChar = currentExpression.last()
                    if (lastChar in "+-×÷^") {
                        textExpression.text = currentExpression.dropLast(1) + value
                    } else {
                        textExpression.append(value)
                    }
                }
            }
        }
    }

    private fun setupParenthesisButtons() {
        findViewById<AppCompatButton>(R.id.button_open_parenthesis).setOnClickListener {
            if (textExpression.text.toString() == "0") {
                textExpression.text = "("
            } else {
                textExpression.append("(")
            }
        }

        findViewById<AppCompatButton>(R.id.button_close_parenthesis).setOnClickListener {
            textExpression.append(")")
        }
    }

    private fun setupDotButton() {
        findViewById<AppCompatButton>(R.id.button_dot).setOnClickListener {
            val currentExpression = textExpression.text.toString()
            val lastNumber = currentExpression.split(Regex("[+\\-×÷^()]")).last()
            if (!lastNumber.contains(".")) {
                textExpression.append(".")
            }
        }
    }

    private fun setupSqrtButton() {
        findViewById<AppCompatButton>(R.id.button_sqrt).setOnClickListener {
            val currentExpression = textExpression.text.toString()
            if (currentExpression == "0" || currentExpression.isEmpty()) {
                textExpression.text = "√("
            } else {
                textExpression.append("√(")
            }
        }
    }


    private fun resetExpressionAndResult() {
        textExpression.textSize = 50F
        textResult.textSize = 30F
        textResult.setTextColor(resources.getColor(R.color.green))
        textExpression.text = "0"
        textResult.text = "0"
    }

    private fun isError(result: String): Boolean {
        return result.startsWith("Erreur")
    }
}
