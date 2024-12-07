package com.ing3.promotioning3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class StudentAdapter(private val studentList: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]

        // Remplir les données
        holder.studentName.text = "${student.firstName} ${student.name}"

        // Charger l'image depuis drawable avec Glide
        val imageName = student.imageUrl
        val resourceId = holder.itemView.context.resources.getIdentifier(
            imageName,
            "drawable",
            holder.itemView.context.packageName
        )

        // Vérification si l'image existe
        if (resourceId != 0) {
            Glide.with(holder.studentImage.context)
                .load(resourceId)
                .placeholder(R.drawable.defaut_student_image) // Image par défaut si absent
                .into(holder.studentImage)
        } else {
            // Charger une image par défaut si l'image n'est pas trouvée
            Glide.with(holder.studentImage.context)
                .load(R.drawable.defaut_student_image)
                .into(holder.studentImage)
        }
    }

    override fun getItemCount() = studentList.size

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentImage: ImageView = itemView.findViewById(R.id.studentImage)
        val studentName: TextView = itemView.findViewById(R.id.studentName)
    }
}
