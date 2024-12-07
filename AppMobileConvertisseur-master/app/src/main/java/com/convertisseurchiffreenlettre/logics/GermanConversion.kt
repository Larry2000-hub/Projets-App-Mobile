package com.convertisseurchiffreenlettre.logics

class GermanConversion(val number : Long) {

    fun conversion() : String {
        return convertToGerman(number)
    }

    private fun convertToGerman(number: Long): String {
        return when {
            number in 0..10 -> convertSmallNumberToGerman(number)
            number in 11..19 -> convertTeensToGerman(number)
            number in 20..99 -> convertTensToGerman(number)
            number in 100..999 -> convertHundredsToGerman(number)
            number in 1000..999999 -> convertThousandsToGerman(number)
            number in 1000000..999999999 -> convertMillionsToGerman(number)
            number in 1000000000..999999999999 -> convertMilliardsToGerman(number)
            number in 1000000000000..999999999999999 -> convertBillionsToGerman(number)
            else -> "Zahl zu groß"
        }
    }

    // Conversion des petits nombres
    private fun convertSmallNumberToGerman(number: Long): String {
        return when (number) {
            0L -> "null"
            1L -> "eins"
            2L -> "zwei"
            3L -> "drei"
            4L -> "vier"
            5L -> "fünf"
            6L -> "sechs"
            7L -> "sieben"
            8L -> "acht"
            9L -> "neun"
            10L -> "zehn"
            else -> "Nicht unterstützte Zahl"
        }
    }

    // Conversion des nombres entre 11 et 19
    private fun convertTeensToGerman(number: Long): String {
        return when (number) {
            11L -> "elf"
            12L -> "zwölf"
            13L -> "dreizehn"
            14L -> "vierzehn"
            15L -> "fünfzehn"
            16L -> "sechzehn"
            17L -> "siebzehn"
            18L -> "achtzehn"
            19L -> "neunzehn"
            else -> "Nicht unterstützte Zahl"
        }
    }

    // Conversion des dizaines
    private fun convertTensToGerman(number: Long): String {
        val tens = number / 10
        val units = number % 10
        return when (tens) {
            2L -> if (units == 0L) "zwanzig" else "${convertSmallNumberToGerman(units)}undzwanzig"
            3L -> if (units == 0L) "dreißig" else "${convertSmallNumberToGerman(units)}unddreißig"
            4L -> if (units == 0L) "vierzig" else "${convertSmallNumberToGerman(units)}undvierzig"
            5L -> if (units == 0L) "fünfzig" else "${convertSmallNumberToGerman(units)}undfünfzig"
            6L -> if (units == 0L) "sechzig" else "${convertSmallNumberToGerman(units)}undsechzig"
            7L -> if (units == 0L) "siebzig" else "${convertSmallNumberToGerman(units)}undsiebzig"
            8L -> if (units == 0L) "achtzig" else "${convertSmallNumberToGerman(units)}undachtzig"
            9L -> if (units == 0L) "neunzig" else "${convertSmallNumberToGerman(units)}undneunzig"
            else -> "Nicht unterstützte Zahl"
        }
    }

    // Conversion des centaines
    private fun convertHundredsToGerman(number: Long): String {
        val hundreds = number / 100
        val remainder = number % 100
        val hundredsPart = if (hundreds == 1L) "einhundert" else "${convertSmallNumberToGerman(hundreds)}hundert"
        return if (remainder == 0L) hundredsPart else "$hundredsPart ${convertToGerman(remainder)}"
    }

    // Conversion des milliers
    private fun convertThousandsToGerman(number: Long): String {
        val thousands = number / 1000
        val remainder = number % 1000
        val thousandsPart = if (thousands == 1L) "eintausend" else "${convertToGerman(thousands)}tausend"
        return if (remainder == 0L) thousandsPart else "$thousandsPart ${convertToGerman(remainder)}"
    }

    // Conversion des millions
    private fun convertMillionsToGerman(number: Long): String {
        val millions = number / 1000000
        val remainder = number % 1000000
        val millionsPart = if (millions == 1L) "eine Million" else "${convertToGerman(millions)} Millionen"
        return if (remainder == 0L) millionsPart else "$millionsPart ${convertToGerman(remainder)}"
    }

    // Conversion des milliards
    private fun convertMilliardsToGerman(number: Long): String {
        val billions = number / 1000000000
        val remainder = number % 1000000000
        val billionsPart = if (billions == 1L) "eine Milliarde" else "${convertToGerman(billions)} Milliarden"
        return if (remainder == 0L) billionsPart else "$billionsPart ${convertToGerman(remainder)}"
    }
    // Conversion des billions
    private fun convertBillionsToGerman(number: Long): String {
        val billions = number / 1000000000000
        val remainder = number % 1000000000000
        val billionsPart = if (billions == 1L) "eine Billion" else "${convertToGerman(billions)} Billionen"
        return if (remainder == 0L) billionsPart else "$billionsPart ${convertToGerman(remainder)}"
    }

}