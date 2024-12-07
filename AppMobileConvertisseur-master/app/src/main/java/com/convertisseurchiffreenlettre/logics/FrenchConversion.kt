package com.convertisseurchiffreenlettre.logics

class FrenchConversion (private val number: Long) {

    fun conversion () : String{
        return convertToFrench(number)
    }

    private fun convertToFrench(number: Long): String {
        return when {
            number in 0..10 -> convertSmallNumberToFrench(number)
            number in 11..19 -> convertTeensToFrench(number)
            number in 20..99 -> convertTensToFrench(number)
            number in 100..999 -> convertHundredsToFrench(number)
            number in 1000..999999 -> convertThousandsToFrench(number)
            number in 1000000..999999999 -> convertMillionsToFrench(number)
            number in 1000000000..999999999999 -> convertMilliardsToFrench(number)
            number in 1000000000000..999999999999999 -> convertBillionsToFrench(number)
            else -> "Nombre trop grand"
        }
    }

    // Convertion pour les petits nombres
    private fun convertSmallNumberToFrench(number: Long): String {
        return when (number) {
            0L -> "zéro"
            1L -> "un"
            2L -> "deux"
            3L -> "trois"
            4L -> "quatre"
            5L -> "cinq"
            6L -> "six"
            7L -> "sept"
            8L -> "huit"
            9L -> "neuf"
            10L -> "dix"
            else -> "Nombre non pris en charge"
        }
    }

    // Convertion pour les nombres de 11 à 19
    private fun convertTeensToFrench(number: Long): String {
        return when (number) {
            11L -> "onze"
            12L -> "douze"
            13L -> "treize"
            14L -> "quatorze"
            15L -> "quinze"
            16L -> "seize"
            17L -> "dix-sept"
            18L -> "dix-huit"
            19L -> "dix-neuf"
            else -> "Nombre non pris en charge"
        }
    }

    // Convertion pour les dizaines
    private fun convertTensToFrench(number: Long): String {
        val tens = number / 10
        val units = number % 10

        return when (tens) {
            2L -> if (units == 0L) "vingt" else "vingt-${convertSmallNumberToFrench(units)}"
            3L -> if (units == 0L) "trente" else "trente-${convertSmallNumberToFrench(units)}"
            4L -> if (units == 0L) "quarante" else "quarante-${convertSmallNumberToFrench(units)}"
            5L -> if (units == 0L) "cinquante" else "cinquante-${convertSmallNumberToFrench(units)}"
            6L -> if (units == 0L) "soixante" else "soixante-${convertSmallNumberToFrench(units)}"
            7L -> if (units == 0L) "soixante-dix" else if (units == 1L) "soixante-onze" else "soixante-${convertTeensToFrench(10 + units)}"
            8L -> if (units == 0L) "quatre-vingts" else "quatre-vingt-${convertSmallNumberToFrench(units)}"
            9L -> if (units == 0L) "quatre-vingt-dix" else if (units == 1L) "quatre-vingt-onze" else "quatre-vingt-${convertTeensToFrench(10 + units)}"
            else -> "Nombre non pris en charge"
        }
    }

    // Convertion pour les centaines
    private fun convertHundredsToFrench(number: Long): String {
        val hundreds = number / 100
        val remainder = number % 100
        return when (hundreds) {
            1L -> if (remainder == 0L) "cent" else "cent ${convertToFrench(remainder)}"
            2L -> if (remainder == 0L) "deux cents" else "deux cents ${convertToFrench(remainder)}"
            3L -> if (remainder == 0L) "trois cents" else "trois cents ${convertToFrench(remainder)}"
            4L -> if (remainder == 0L) "quatre cents" else "quatre cents ${convertToFrench(remainder)}"
            5L -> if (remainder == 0L) "cinq cents" else "cinq cents ${convertToFrench(remainder)}"
            6L -> if (remainder == 0L) "six cents" else "six cents ${convertToFrench(remainder)}"
            7L -> if (remainder == 0L) "sept cents" else "sept cents ${convertToFrench(remainder)}"
            8L -> if (remainder == 0L) "huit cents" else "huit cents ${convertToFrench(remainder)}"
            9L -> if (remainder == 0L) "neuf cents" else "neuf cents ${convertToFrench(remainder)}"
            else -> "Nombre non pris en charge"
        }
    }

    // Conversion pour les milliers
    private fun convertThousandsToFrench(number: Long): String {
        val thousands = number / 1000
        val remainder = number % 1000
        val thousandsPart = if (thousands == 1L) "mille" else "${convertToFrench(thousands)} mille"
        return if (remainder == 0L) thousandsPart else "$thousandsPart ${convertToFrench(remainder)}"
    }

    // Conversion pour les millions
    private fun convertMillionsToFrench(number: Long): String {
        val millions = number / 1000000
        val remainder = number % 1000000
        val millionsPart = if (millions == 1L) "un million" else "${convertToFrench(millions)} millions"
        return if (remainder == 0L) millionsPart else "$millionsPart ${convertToFrench(remainder)}"
    }

    // Conversion pour les milliards
    private fun convertMilliardsToFrench(number: Long): String {
        val milliards = number / 1000000000
        val remainder = number % 1000000000
        val milliardsPart = if (milliards == 1L) "un milliard" else "${convertToFrench(milliards)} milliards"
        return if (remainder == 0L) milliardsPart else "$milliardsPart ${convertToFrench(remainder)}"
    }

    // Conversion pour les billions
    private fun convertBillionsToFrench(number: Long): String {
        val billions = number / 1000000000000L
        val remainder = number % 1000000000000L
        val billionsPart = if (billions == 1L) "un billion" else "${convertToFrench(billions)} billions"
        return if (remainder == 0L) billionsPart else "$billionsPart ${convertToFrench(remainder)}"
    }
}