package com.ing3.promotioning3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SubjectsActivity : AppCompatActivity() {

    // Liste des matières
    private val subjectList = mutableListOf(
        Subject("Programmation Mobile"),
        Subject("Programmation Système"),
        Subject("Analyse de données"),
        Subject("Méthodes Formelles de Conception"),
        Subject("Système d'Informations Géographiques, Télédétection"),
        Subject("Rédaction Scientifique"),
        Subject("Installation et administration des réseaux"),
        Subject("Interconnection et Réseaux haut débit+MSR"),
        Subject("Base de Données Avancées"),
        Subject("Conduite de Projets Informatiques"),
        Subject("Informatique Décisionnelle Introduction"),
        Subject("Qualité logiciel"),
        Subject("Objets connectés, Internet des Objets"),
        Subject("Techniques contractuelles"),
        Subject("Stage - Mémoire")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subjects)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.subjectRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val subjectAdapter = SubjectAdapter(subjectList)
        recyclerView.adapter = subjectAdapter
    }

}