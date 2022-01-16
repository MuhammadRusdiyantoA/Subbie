package com.example.room_muhammadrusdiyanto_18

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_muhammadrusdiyanto_18.R
import com.example.room_muhammadrusdiyanto_18.room.SubjectDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchActivity : AppCompatActivity() {

    val db by lazy { SubjectDb(this) }
    lateinit var itemAdapter: ItemAdapter
    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

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

    private fun setupListener() {
        val backButton = findViewById<ImageView>(R.id.nav_back4)
        backButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val searchButton = findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener{
            querySetup()
        }
    }

    private fun querySetup() {
        val searchVariable = findViewById<EditText>(R.id.in_search).text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            val subjects = db.subjectDao().getSearch(searchVariable)
            withContext(Dispatchers.Main){
                itemAdapter.setData(subjects)
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter(arrayListOf()) {
            val intent = Intent(this, UpDelActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
        search_viewer.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = itemAdapter
        }
    }
}