package com.example.room_muhammadrusdiyanto_18.room

import androidx.room.*

@Dao
interface SubjectDao {

    @Insert
    suspend fun addSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query ("SELECT * FROM subject")
    suspend fun getSubjects():List<Subject>

    @Query ("select * from subject order by subj_id desc limit 3")
    suspend fun getNewSubj():List<Subject>

    @Query ("select * from subject order by subj_name")
    suspend fun getGroupSubjName():List<Subject>

    @Query ("select * from subject order by subj_teach")
    suspend fun getGroupSubjTeach():List<Subject>

    //Kalo mau pake variable, pake tanda ':' sebelum nama variabel
    @Query ("select * from subject where subj_name like '%'||:x||'%' or subj_teach like '%'||:x||'%'")
    suspend fun getSearch(x : String):List<Subject>
}