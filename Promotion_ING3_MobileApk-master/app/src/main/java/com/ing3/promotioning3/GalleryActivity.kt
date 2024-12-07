package com.ing3.promotioning3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GalleryActivity : AppCompatActivity() {

    // La liste des étudiants
    private val studentList = mutableListOf(
        Student("APEDO", "Kossi Emmanuel"),
        Student("NZOGHE ASSENGONE", "Yolande Saly"),
        Student("DZIDJINYO", "Komlan Maurice Yann"),
        Student("BAWANA", "Theodore"),
        Student("BELIGONE", "Lary"),
        Student("MAYOMBO MOUBAROU", "Ted Orly"),
        Student("REMADJI", "Eric"),
        Student("BEBANE MOUKOUMBI", "Marina"),
        Student("ZENKOUEREY", "Doguy"),
        Student("NKILI OBELE", "Fifi Karen"),
        Student("MBOYI", "Bienfait")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialisation du RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Définition du layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialisation de l'adaptateur
        val studentAdapter = StudentAdapter(studentList)

        // Lier l'adaptateur au RecyclerView
        recyclerView.adapter = studentAdapter


        // Récupérer le FloatingActionButton
        val fabAddPhoto: ImageView = findViewById(R.id.fabAddPhoto)

        // Ajouter un OnClickListener pour ouvrir le formulaire
        fabAddPhoto.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }



    }
}