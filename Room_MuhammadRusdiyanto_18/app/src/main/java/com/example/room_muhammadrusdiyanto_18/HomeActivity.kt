package com.example.room_muhammadrusdiyanto_18

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_muhammadrusdiyanto_18.room.SubjectDb
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class HomeActivity : AppCompatActivity() {

    val db by lazy { SubjectDb(this) }
    lateinit var  itemAdapter: ItemAdapter
    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val subjects = db.subjectDao().getNewSubj()
            withContext(Dispatchers.Main){
                itemAdapter.setData(subjects)
            }
        }
    }

    private fun setupListener() {
        val navMain = findViewById<Button>(R.id.nav_list)
        navMain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val navSearch = findViewById<Button>(R.id.nav_search)
        navSearch.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        val today = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK)
        val dayText = findViewById<TextView>(R.id.home_day)

        when (today) {
            1 -> { dayText.setText("SUNDAY") }
            2 -> { dayText.setText("MONDAY") }
            3 -> { dayText.setText("TUESDAY") }
            4 -> { dayText.setText("WEDNESDAY") }
            5 -> { dayText.setText("THURSDAY") }
            6 -> { dayText.setText("FRIDAY") }
            7 -> { dayText.setText("SATURDAY") }
            else -> {dayText.setText("TODAY")}
        }
    }

    fun setupRecyclerView() {
        itemAdapter = ItemAdapter(arrayListOf()) {
            val intent = Intent(this, UpDelActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
        new_viewer.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = itemAdapter
        }
    }
}