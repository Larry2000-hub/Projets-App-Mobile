package com.ing3.promotioning3.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PhotoUtils(
    private val activity: Activity,
    private val imageView: ImageView,
    private val onPhotoSelected: (Bitmap?, Uri?) -> Unit
) {

    companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 101
        const val GALLERY_REQUEST_CODE = 102
        const val CAMERA_REQUEST_CODE = 103
    }

    var capturedBitmap: Bitmap? = null
    var selectedImageUri: Uri? = null

    fun showPhotoSourceDialog() {
        val options = arrayOf("Prendre une photo", "Choisir depuis la galerie")
        AlertDialog.Builder(activity)
            .setTitle("Ajouter une photo")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> checkCameraPermission()
                    1 -> openGallery()
                }
            }
            .show()
    }

    private fun checkCameraPermission() {
        if (isCameraPermissionGranted()) {
            openCamera()
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun isCameraPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        activity.startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    capturedBitmap = data?.extras?.get("data") as? Bitmap
                    capturedBitmap?.let {
                        imageView.setImageBitmap(it)
                        onPhotoSelected(it, null)
                    }
                }
                GALLERY_REQUEST_CODE -> {
                    selectedImageUri = data?.data
                    selectedImageUri?.let { uri ->
                        imageView.setImageURI(uri)
                        onPhotoSelected(null, uri)
                    }
                }
            }
        }
    }
}
