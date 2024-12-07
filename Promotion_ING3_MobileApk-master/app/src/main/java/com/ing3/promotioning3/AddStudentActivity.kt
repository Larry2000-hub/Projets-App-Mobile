package com.ing3.promotioning3

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

class AddStudentActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null
    private var capturedBitmap: Bitmap? = null

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 101
        private const val GALLERY_REQUEST_CODE = 102
        private const val CAMERA_REQUEST_CODE = 103
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val addPhotoButton: Button = findViewById(R.id.btnSelectPhoto)
        val imageView: ImageView = findViewById(R.id.photoPreview)
        val saveButton: Button = findViewById(R.id.btnSaveStudent)
        val firstNameEditText : EditText = findViewById(R.id.firstName)
        val lastNameEditText: EditText = findViewById(R.id.lastName)

        // Gérer la sélection ou la capture de photo
        addPhotoButton.setOnClickListener {
            showPhotoSourceDialog()
        }

        // Sauvegarde des informations
        saveButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedImageUri == null && capturedBitmap == null) {
                Toast.makeText(this, "Veuillez ajouter une photo.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sauvegarde de la photo et des informations
            saveStudent(firstName, lastName, capturedBitmap, selectedImageUri)
        }
    }

    private fun showPhotoSourceDialog() {
        // Créer une boîte de dialogue pour permettre à l'utilisateur de choisir
        val options = arrayOf("Prendre une photo", "Choisir depuis la galerie")

        // Afficher la boîte de dialogue avec les options
        AlertDialog.Builder(this)
            .setTitle("Ajouter une photo")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> { // Prendre une photo
                        if (isCameraPermissionGranted()) {
                            openCamera()
                        } else {
                            requestCameraPermission()
                        }
                    }
                    1 -> { // Choisir depuis la galerie
                        openGallery()
                    }
                }
            }
            .show()
    }


    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    // Gestion dela caméra pour la prise de photo
    @SuppressLint("QueryPermissionsNeeded")
    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        } else {
            showError("Aucune application de caméra trouvée.")
        }
    }

    @SuppressLint("IntentReset")
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                showError("Permission caméra refusée.")
            }
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    capturedBitmap = data?.extras?.get("data") as? Bitmap
                    capturedBitmap?.let{findViewById<ImageView>(R.id.photoPreview).setImageBitmap(it)}
                }
                GALLERY_REQUEST_CODE -> {
                    val selectedImageUri = data?.data
                    // Gérer l'image sélectionnée ici
                    selectedImageUri?.let{
                        val inputStream = contentResolver.openInputStream(it)
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        findViewById<ImageView>(R.id.photoPreview).setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    private fun saveStudent(
        firstName: String,
        lastName: String,
        bitmap: Bitmap?,
        imageUri: Uri?
    ) {
        try {
            val imageFile = File(getExternalFilesDir(null), "${firstName}_${lastName}.jpg")
            if (bitmap != null) {
                val outputStream = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream)
                outputStream.flush()
                outputStream.close()
            }

            // Logique de sauvegarde des informations (dans une base de données ou un fichier)
            Toast.makeText(this, "Étudiant sauvegardé avec succès.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erreur lors de la sauvegarde.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
