package com.example.room_muhammadrusdiyanto_18.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Subject::class],
    version = 1
)


abstract class SubjectDb : RoomDatabase() {
    abstract fun subjectDao() : SubjectDao
    companion object {
        @Volatile private var instance : SubjectDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SubjectDb::class.java,
            "subjects1.db"
        ).build()
    }
}