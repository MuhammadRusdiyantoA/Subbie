package com.example.room_muhammadrusdiyanto_18

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.room_muhammadrusdiyanto_18.room.Subject
import com.example.room_muhammadrusdiyanto_18.room.SubjectDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy {SubjectDb(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.hide()

        setupListener()
    }

    fun setupListener(){
        val backButton = findViewById<ImageView>(R.id.nav_back1)
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val addData = findViewById<Button>(R.id.do_add)
        addData.setOnClickListener{
            setData()
        }
    }

    private fun setData() {
        val subjTitle = findViewById<EditText>(R.id.in_title)
        val subjTeach = findViewById<EditText>(R.id.in_teacher)

        if (subjTitle.text.toString().isEmpty()) {
            subjTitle.error = "Please enter a name for the subject..."
            subjTitle.requestFocus()
            return
        }
        if (subjTeach.toString().isEmpty()) {
            subjTeach.error = "Please enter the subject teacher's name..."
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            db.subjectDao().addSubject(
                Subject(0, subjTitle.text.toString(), subjTeach.text.toString())
            )
            finish()
        }
        Toast.makeText(this, "Subject Added successfully!", Toast.LENGTH_SHORT).show()
    }
}