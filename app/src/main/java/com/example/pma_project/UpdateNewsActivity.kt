package com.example.pma_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.pma_project.Constants.BUNDLE_NEWS_ID
import com.example.pma_project.databinding.ActivityUpdateNewsBinding
import com.google.android.material.snackbar.Snackbar

class UpdateNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNewsBinding
    private lateinit var newsEntity: NewsEntity
    private var newsId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        intent.extras?.let {
            newsId = it.getInt(BUNDLE_NEWS_ID)
        }
        binding.apply {
            defaultTitle = newsDB.dao().getNews(newsId).newsTile
            defaultDesc = newsDB.dao().getNews(newsId).newsDesc
            editTitle.setText(defaultTitle)
            editDesc.setText(defaultDesc)
            btnDelete.setOnClickListener {
                newsEntity= NewsEntity(newsId,defaultTitle,defaultDesc)
                newsDB.dao().deleteNews(newsEntity)
                startActivity(Intent(this@UpdateNewsActivity,MainActivity::class.java))
                finish()
            }
            btnSave.setOnClickListener {
                val title = editTitle.text.toString()
                val desc=editDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    newsEntity= NewsEntity(newsId,title,desc)
                    newsDB.dao().updateNews(newsEntity)
                    startActivity(Intent(this@UpdateNewsActivity,MainActivity::class.java))
                    finish()
                }
                else{
                    Snackbar.make(it,"Title and Description cannot be Empty", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}