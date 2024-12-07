package com.ing3.promotioning3

data class Student(
    val name: String,
    val firstName: String
) {
    // Propriété calculée pour imageUrl
    val imageUrl: String
        get() {

            // Supprime les espaces et met en minuscule
            val formattedName = name.replace(" ", "").lowercase()
            val formattedFirstName = firstName.replace(" ", "").lowercase()
            return "${formattedName}_${formattedFirstName}"
        }
}
