package com.convertisseurchiffreenlettre.logics

class EnglishConversion (val number : Long){

    fun conversion() : String{
        return convertToEnglish(number)
    }
    //Convertion en Anglais
    // Conversion pour l'anglais avec Long
    private fun convertToEnglish(number: Long): String {
        return when {
            number in 0..10 -> convertSmallNumberToEnglish(number)
            number in 11..19 -> convertTeensToEnglish(number)
            number in 20..99 -> convertTensToEnglish(number)
            number in 100..999 -> convertHundredsToEnglish(number)
            number in 1000..999999 -> convertThousandsToEnglish(number)
            number in 1000000..999999999 -> convertMillionsToEnglish(number)
            number in 1000000000..999999999999 -> convertMilliardsToEnglish(number)
            number in 1000000000000..999999999999999 -> convertBillionsToEnglish(number)
            else -> "Number too large"
        }
    }

    // Conversion des petites unités
    private fun convertSmallNumberToEnglish(number: Long): String {
        return when (number) {
            0L -> "zero"
            1L -> "one"
            2L -> "two"
            3L -> "three"
            4L -> "four"
            5L -> "five"
            6L -> "six"
            7L -> "seven"
            8L -> "eight"
            9L -> "nine"
            10L -> "ten"
            else -> "Unsupported number"
        }
    }

    // Conversion des nombres entre 11 et 19
    private fun convertTeensToEnglish(number: Long): String {
        return when (number) {
            11L -> "eleven"
            12L -> "twelve"
            13L -> "thirteen"
            14L -> "fourteen"
            15L -> "fifteen"
            16L -> "sixteen"
            17L -> "seventeen"
            18L -> "eighteen"
            19L -> "nineteen"
            else -> "Unsupported number"
        }
    }

    // Conversion des dizaines
    private fun convertTensToEnglish(number: Long): String {
        val tens = number / 10
        val units = number % 10

        return when (tens) {
            2L -> if (units == 0L) "twenty" else "twenty-${convertSmallNumberToEnglish(units)}"
            3L -> if (units == 0L) "thirty" else "thirty-${convertSmallNumberToEnglish(units)}"
            4L -> if (units == 0L) "forty" else "forty-${convertSmallNumberToEnglish(units)}"
            5L -> if (units == 0L) "fifty" else "fifty-${convertSmallNumberToEnglish(units)}"
            6L -> if (units == 0L) "sixty" else "sixty-${convertSmallNumberToEnglish(units)}"
            7L -> if (units == 0L) "seventy" else "seventy-${convertSmallNumberToEnglish(units)}"
            8L -> if (units == 0L) "eighty" else "eighty-${convertSmallNumberToEnglish(units)}"
            9L -> if (units == 0L) "ninety" else "ninety-${convertSmallNumberToEnglish(units)}"
            else -> "Unsupported number"
        }
    }

    // Conversion des centaines
    private fun convertHundredsToEnglish(number: Long): String {
        val hundreds = number / 100
        val remainder = number % 100
        val hundredsPart = "${convertSmallNumberToEnglish(hundreds)} hundred"
        return if (remainder == 0L) hundredsPart else "$hundredsPart ${convertToEnglish(remainder)}"
    }

    // Conversion des milliers
    private fun convertThousandsToEnglish(number: Long): String {
        val thousands = number / 1000
        val remainder = number % 1000
        val thousandsPart = "${convertToEnglish(thousands)} thousand"
        return if (remainder == 0L) thousandsPart else "$thousandsPart ${convertToEnglish(remainder)}"
    }

    // Conversion des millions
    private fun convertMillionsToEnglish(number: Long): String {
        val millions = number / 1000000
        val remainder = number % 1000000
        val millionsPart = if (millions == 1L) "one million" else "${convertToEnglish(millions)} million"
        return if (remainder == 0L) millionsPart else "$millionsPart ${convertToEnglish(remainder)}"
    }

    // Conversion des milliards
    private fun convertMilliardsToEnglish(number: Long): String {
        val billions = number / 1000000000
        val remainder = number % 1000000000
        val billionsPart = if (billions == 1L) "one billion" else "${convertToEnglish(billions)} billion"
        return if (remainder == 0L) billionsPart else "$billionsPart ${convertToEnglish(remainder)}"
    }

    // Conversion des billions
    private fun convertBillionsToEnglish(number: Long): String {
        val trillions = number / 1000000000000
        val remainder = number % 1000000000000
        val trillionsPart = if (trillions == 1L) "one trillion" else "${convertToEnglish(trillions)} trillion"
        return if (remainder == 0L) trillionsPart else "$trillionsPart ${convertToEnglish(remainder)}"
    }


}