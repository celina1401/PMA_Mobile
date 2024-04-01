package com.example.pma_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.pma_project.databinding.ActivityAddNewsBinding
import com.example.pma_project.databinding.ItemNewsBinding
import com.google.android.material.snackbar.Snackbar

class AddNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddNewsBinding
    private lateinit var newsEntity: NewsEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnSave.setOnClickListener {
                val title = editTitle.text.toString()
                val desc = editDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    newsEntity = NewsEntity(0,title,desc)
                    newsDB.dao().insertNews(newsEntity)
                    finish()
                }
                else{
                    Snackbar.make(it,"Title and Description cannot be Empty",Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}