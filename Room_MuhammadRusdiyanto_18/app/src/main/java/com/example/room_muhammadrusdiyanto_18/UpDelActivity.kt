package com.example.room_muhammadrusdiyanto_18

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.room_muhammadrusdiyanto_18.room.Subject
import com.example.room_muhammadrusdiyanto_18.room.SubjectDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpDelActivity : AppCompatActivity() {
    val db by lazy { SubjectDb(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_del)

        supportActionBar?.hide()

        setupListener()

        val subjData = intent.getParcelableExtra<Subject>(MainActivity.INTENT_PARCELABLE)

        val in_title = findViewById<EditText>(R.id.ed_title)
        val in_teach = findViewById<EditText>(R.id.ed_teacher)

        var id = 0
        var name = ""
        var teacher = ""

        if (subjData != null){
            id = subjData.subj_id
            name = subjData.subj_name
            teacher = subjData.subj_teach
        }

        in_title.setText(subjData?.subj_name)
        in_teach.setText(subjData?.subj_teach)

        val updateButton = findViewById<Button>(R.id.do_up)
        updateButton.setOnClickListener{
            setData(id = id)
        }

        val deleteButton = findViewById<Button>(R.id.do_del)
        deleteButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.subjectDao().deleteSubject(
                    Subject(id, name, teacher)
                )
                finish()
            }
            Toast.makeText(this, "Subject is deleted successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setData(id: Int) {
        val ind_name = findViewById<EditText>(R.id.ed_title)
        val ind_teach = findViewById<EditText>(R.id.ed_teacher)

        if (ind_name.text.toString().isEmpty()) {
            ind_name.error = "Please enter a name for the subject..."
            ind_name.requestFocus()
            return
        }
        if (ind_teach.text.toString().isEmpty()) {
            ind_teach.error = "Please enter the subject teacher's name..."
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            db.subjectDao().updateSubject(
                Subject(id, ind_name.text.toString(), ind_teach.text.toString())
            )
            finish()
        }
        Toast.makeText(this, "Subject is updated successsfully!", Toast.LENGTH_SHORT).show()
    }

    fun setupListener() {
        val backButton = findViewById<ImageView>(R.id.nav_back3)
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}