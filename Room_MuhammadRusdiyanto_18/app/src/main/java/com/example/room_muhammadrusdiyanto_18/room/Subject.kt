package com.example.room_muhammadrusdiyanto_18.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Subject (
    @PrimaryKey(autoGenerate = true)
    val subj_id: Int,
    val subj_name: String,
    val subj_teach: String
        ) : Parcelable