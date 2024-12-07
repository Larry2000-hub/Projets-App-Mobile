package com.convertisseurchiffreenlettre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import android.app.AlertDialog
import android.text.TextWatcher
import android.text.Editable
import com.convertisseurchiffreenlettre.logics.EnglishConversion
import com.convertisseurchiffreenlettre.logics.FrenchConversion
import com.convertisseurchiffreenlettre.logics.GermanConversion
import com.convertisseurchiffreenlettre.logics.SpanishConversion
import com.convertisseurchiffreenlettre.shares.DialogBotYesNo

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var selectLanguage: Button
    private lateinit var resultText: TextView
    private var selectedLanguage: String = "fr"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des vues
        inputNumber = findViewById(R.id.inputNumber)
        selectLanguage = findViewById(R.id.selectLanguage)
        resultText = findViewById(R.id.resultText)


        // Gestion de la sélection de langue
        selectLanguage.setOnClickListener {
            showLanguageSelectionDialog()
        }

        // Gestion de l'entrée utilisateur en temps réel
        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    try {
                        s.toString().toLong() // Vérifie si l'entrée est un nombre valide
                        convertAndDisplayResult()
                    } catch (e: NumberFormatException) {
                        resultText.text = "Entrée invalide, veuillez saisir un nombre entier."
                    }
                } else {
                    resultText.text = ""
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun showLanguageSelectionDialog() {
        // Liste des langues et leurs codes correspondants
        val languages = arrayOf("Français", "Anglais", "Espagnol", "Allemand")
        val languageCodes = arrayOf("fr", "en", "es", "de")

        // Inflate le layout personnalisé pour le dialogue
        val dialogView = layoutInflater.inflate(R.layout.dialog_select_language, null)

        // Création du constructeur de la boîte de dialogue
        val builder = AlertDialog.Builder(this).apply {
            setView(dialogView)
        }

        // Initialisation des vues dans le layout de dialogue
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val languageListView = dialogView.findViewById<ListView>(R.id.languageListView)

        // Adaptateur pour afficher la liste des langues
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages)
        languageListView.adapter = adapter

        // Création de la boîte de dialogue
        val dialog = builder.create()

        // Gestion des clics sur les éléments de la liste
        languageListView.setOnItemClickListener { _, _, which, _ ->
            selectedLanguage = languageCodes[which]
            selectLanguage.text = languages[which] // Mise à jour du texte du bouton
            Toast.makeText(this, "Langue sélectionnée : ${languages[which]}", Toast.LENGTH_SHORT).show()
            convertAndDisplayResult()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun convertAndDisplayResult() {
        val numberText = inputNumber.text.toString()
        try {
            val number = numberText.toLong()
            if (number < 0) {
                resultText.text = "Les nombres négatifs ne sont pas supportés."
            } else {
                val result = convertNumberToWords(number, selectedLanguage)
                resultText.text = result
            }
        } catch (e: NumberFormatException) {
            resultText.text = "Entrée invalide, veuillez saisir un nombre entier."
        }
    }

    private fun convertNumberToWords(number: Long, language: String): String {
        return when (language) {
            "fr" -> FrenchConversion(number).conversion()
            "en" -> EnglishConversion(number).conversion()
            "es" -> SpanishConversion(number).conversion()
            "de" -> GermanConversion(number).conversion()
            else -> "Langue non supportée."
        }
    }
}
