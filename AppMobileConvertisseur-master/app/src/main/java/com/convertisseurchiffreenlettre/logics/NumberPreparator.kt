package com.convertisseurchiffreenlettre.logics

class NumberPreparator(val number: Double) {

    fun getNumberParts(): Map<String, Any> {
        val numberInString = number.toString().replace(",", ".")
        val indexOfCommonInNumber = numberInString.indexOf('.')
        val partieEntierString = numberInString.substring(0, indexOfCommonInNumber)
        val partieDecimalString = numberInString.substring(indexOfCommonInNumber + 1)

        val partieEntier = partieEntierString.toInt()
        val partieDecimal = partieDecimalString.toIntOrNull() ?: 0 // Gérer les cas où la partie décimale est vide

        // Calculer le nombre de zéros au début de la partie décimale
        val numberOfZeroStartDecimalPart = partieDecimalString.length - partieDecimalString.trimStart('0').length

        return mapOf(
            "partieEntier" to partieEntier,
            "partieDecimal" to partieDecimal,
            "numberOfZeroStartDecimalPart" to numberOfZeroStartDecimalPart
        )
    }
}
