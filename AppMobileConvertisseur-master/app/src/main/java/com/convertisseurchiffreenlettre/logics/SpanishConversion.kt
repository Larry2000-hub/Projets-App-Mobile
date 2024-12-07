package com.convertisseurchiffreenlettre.logics

class SpanishConversion(val number : Long) {

    fun conversion(): String{
        return convertToSpanish(number)
    }


    private fun convertToSpanish(number: Long): String {
        return when {
            number in 0..10 -> convertSmallNumberToSpanish(number)
            number in 11..19 -> convertTeensToSpanish(number)
            number in 20..99 -> convertTensToSpanish(number)
            number in 100..999 -> convertHundredsToSpanish(number)
            number in 1000..999999 -> convertThousandsToSpanish(number)
            number in 1000000..999999999 -> convertMillionsToSpanish(number)
            number in 1000000000..999999999999 -> convertMilliardsToSpanish(number)
            number in 1000000000000..999999999999999 -> convertBillionsToSpanish(number)
            else -> "Número demasiado grande"
        }
    }

    // Conversion des unités
    private fun convertSmallNumberToSpanish(number: Long): String {
        return when (number) {
            0L -> "cero"
            1L -> "uno"
            2L -> "dos"
            3L -> "tres"
            4L -> "cuatro"
            5L -> "cinco"
            6L -> "seis"
            7L -> "siete"
            8L -> "ocho"
            9L -> "nueve"
            10L -> "diez"
            else -> "Número no soportado"
        }
    }

    // Conversion des nombres entre 11 et 19
    private fun convertTeensToSpanish(number: Long): String {
        return when (number) {
            11L -> "once"
            12L -> "doce"
            13L -> "trece"
            14L -> "catorce"
            15L -> "quince"
            16L -> "dieciséis"
            17L -> "diecisiete"
            18L -> "dieciocho"
            19L -> "diecinueve"
            else -> "Número no soportado"
        }
    }

    // Conversion des dizaines
    private fun convertTensToSpanish(number: Long): String {
        val tens = number / 10
        val units = number % 10

        return when (tens) {
            2L -> if (units == 0L) "veinte" else "veinti${convertSmallNumberToSpanish(units)}"
            3L -> if (units == 0L) "treinta" else "treinta y ${convertSmallNumberToSpanish(units)}"
            4L -> if (units == 0L) "cuarenta" else "cuarenta y ${convertSmallNumberToSpanish(units)}"
            5L -> if (units == 0L) "cincuenta" else "cincuenta y ${convertSmallNumberToSpanish(units)}"
            6L -> if (units == 0L) "sesenta" else "sesenta y ${convertSmallNumberToSpanish(units)}"
            7L -> if (units == 0L) "setenta" else "setenta y ${convertSmallNumberToSpanish(units)}"
            8L -> if (units == 0L) "ochenta" else "ochenta y ${convertSmallNumberToSpanish(units)}"
            9L -> if (units == 0L) "noventa" else "noventa y ${convertSmallNumberToSpanish(units)}"
            else -> "Número no soportado"
        }
    }

    // Conversion des centaines
    private fun convertHundredsToSpanish(number: Long): String {
        val hundreds = number / 100
        val remainder = number % 100
        val hundredsPart = when (hundreds) {
            1L -> if (remainder == 0L) "cien" else "ciento"
            2L -> "doscientos"
            3L -> "trescientos"
            4L -> "cuatrocientos"
            5L -> "quinientos"
            6L -> "seiscientos"
            7L -> "setecientos"
            8L -> "ochocientos"
            9L -> "novecientos"
            else -> ""
        }
        return if (remainder == 0L) hundredsPart else "$hundredsPart ${convertToSpanish(remainder)}"
    }

    // Conversion des milliers
    private fun convertThousandsToSpanish(number: Long): String {
        val thousands = number / 1000
        val remainder = number % 1000
        val thousandsPart = if (thousands == 1L) "mil" else "${convertToSpanish(thousands)} mil"
        return if (remainder == 0L) thousandsPart else "$thousandsPart ${convertToSpanish(remainder)}"
    }

    // Conversion des millions
    private fun convertMillionsToSpanish(number: Long): String {
        val millions = number / 1000000
        val remainder = number % 1000000
        val millionsPart = if (millions == 1L) "un millón" else "${convertToSpanish(millions)} millones"
        return if (remainder == 0L) millionsPart else "$millionsPart ${convertToSpanish(remainder)}"
    }

    // Conversion des milliards
    private fun convertMilliardsToSpanish(number: Long): String {
        val billions = number / 1000000000
        val remainder = number % 1000000000
        val billionsPart = if (billions == 1L) "un mil millones" else "${convertToSpanish(billions)} mil millones"
        return if (remainder == 0L) billionsPart else "$billionsPart ${convertToSpanish(remainder)}"
    }

    // Conversion des billions (trillions)
    private fun convertBillionsToSpanish(number: Long): String {
        val billions = number / 1000000000000L
        val remainder = number % 1000000000000L
        val billionsPart = if (billions == 1L) "mil billón" else "${convertToSpanish(billions)} mil billones"
        return if (remainder == 0L) billionsPart else "$billionsPart ${convertToSpanish(remainder)}"
    }

}