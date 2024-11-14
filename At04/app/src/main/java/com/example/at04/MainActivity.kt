package com.example.at04

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val profileName = findViewById<TextView>(R.id.nameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJob = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)
        val cafe = findViewById<TextView>(R.id.coffeBebidoHojeText)

        profileName.text = "Marcelo Mikael"
        descriptionText.text = "Estudante de Engenharia de Software na Universidade Federal do Ceará"
        currentJob.text = "Desempregado :("
        cafe.text = "".plus(cafe.text).plus("5 xícaras de café e 5 de energetico")

        val experiencias = listOf(
            "experiencia imaginaria 1",
            "experiencia imaginaria 2",
            "experiencia imaginaria 3"
        )

        for(experiencia in experiencias){
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            experienceLayout.addView(textView)
        }

    }
}