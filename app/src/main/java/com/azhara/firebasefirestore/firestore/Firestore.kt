package com.azhara.firebasefirestore.firestore

import android.content.Context
import com.azhara.firebasefirestore.model.DiaryEntity
import com.google.firebase.firestore.FirebaseFirestore

object Firestore {

    private val db = FirebaseFirestore.getInstance() //Inisialisasi firestore

    fun getDataDiary(): ArrayList<DiaryEntity>{
        val listDiary = ArrayList<DiaryEntity>()
        val data = db.collection("diary")
        data.get().addOnSuccessListener { result ->
            for (data in result){
                val diary = data.toObject(DiaryEntity::class.java)
                diary.id = data.id
                listDiary.add(diary)
            }
        }
        return listDiary
    }

}