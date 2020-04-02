package com.azhara.firebasefirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhara.firebasefirestore.adapter.DiaryAdapter
import com.azhara.firebasefirestore.model.DiaryEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private lateinit var diaryAdapter: DiaryAdapter
    private val db = FirebaseFirestore.getInstance() //Inisialisasi firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diaryAdapter = DiaryAdapter()
        getDataDiary()
        with(rv_diary){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = diaryAdapter
        }
    }

    fun getDataDiary(){
        val data = db.collection("diary")
        val dataDiary = ArrayList<DiaryEntity>()
        loading.visibility = View.VISIBLE
        data.get()
            .addOnSuccessListener { result ->
                loading.visibility = View.GONE
                for (data in result){
                    val diary = data.toObject(DiaryEntity::class.java)
                    diary.id = data.id
                    dataDiary.addAll(listOf(diary))
                    Log.d("Activity", "$diary")
                }
                diaryAdapter.submitList(dataDiary)
            }

    }
}
