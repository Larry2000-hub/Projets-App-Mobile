package com.ing3.calculatrice

import net.objecthunter.exp4j.ExpressionBuilder


object Calculator {
    fun evaluateExpression(expression: String): String {
        return try {
            val formattedExpression = expression
                .replace("×", "*")
                .replace("÷", "/")
                .replace("mod", "%")
                .replace("√(", "sqrt(")

            val result = ExpressionBuilder(formattedExpression).build().evaluate()

            if (result.isNaN()) {
                "Erreur : Résultat invalide"
            } else {
                val rounded = String.format("%.9f", result).trimEnd('0').trimEnd('.')
                rounded
            }
        } catch (e: ArithmeticException) {
            "Erreur : ZeroDivision"
        } catch (e: IllegalArgumentException) {
            "Expression invalide"
        } catch (e: UnsupportedOperationException) {
            "Erreur : Opération non supportée"
        } catch (e: NumberFormatException) {
            "Erreur : Nombre mal formé"
        } catch (e: Exception) {
            "Erreur : Erreur inconnue"
        }
    }
}
