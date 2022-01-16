package com.example.room_muhammadrusdiyanto_18

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room_muhammadrusdiyanto_18.room.Subject
import com.example.room_muhammadrusdiyanto_18.room.SubjectDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { SubjectDb(this) }
    lateinit var itemAdapter: ItemAdapter

//    val radioGroup = findViewById<RadioGroup>(R.id.sort_group)
//    val radioButton1 = findViewById<RadioButton>(R.id.sort_default)
//    val radioButton2 = findViewById<RadioButton>(R.id.sort_subjName)
//    val radioButton3 = findViewById<RadioButton>(R.id.sort_subjTeach)

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val subjects = db.subjectDao().getSubjects()
            withContext(Dispatchers.Main){
                itemAdapter.setData(subjects)
            }
        }
    }

    fun setupListener() {
        val addButton = findViewById<CardView>(R.id.nav_add)
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        val backButton = findViewById<ImageView>(R.id.nav_back2)
        backButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun sortDefault(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            val subjects = db.subjectDao().getSubjects()
            withContext(Dispatchers.Main){
                itemAdapter.setData(subjects)
            }
        }
    }

    fun sortName(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            val subjects = db.subjectDao().getGroupSubjName()
            withContext(Dispatchers.Main){
                itemAdapter.setData(subjects)
            }
        }
    }

    fun sortTeach(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            val subjects = db.subjectDao().getGroupSubjTeach()
            withContext(Dispatchers.Main){
                itemAdapter.setData(subjects)
            }
        }
    }

    fun setupRecyclerView() {
        itemAdapter = ItemAdapter(arrayListOf()) {
            val intent = Intent(this, UpDelActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
        subject_viewer.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = itemAdapter
        }
    }
}