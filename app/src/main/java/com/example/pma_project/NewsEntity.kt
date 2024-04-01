package com.example.pma_project

import android.service.quicksettings.Tile
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pma_project.Constants.NEWS_TABLE

@Entity(tableName = NEWS_TABLE)
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val newsId: Int,
    @ColumnInfo(name = "news_title")
    val newsTile: String,
    @ColumnInfo(name = "news_desc")
    val newsDesc: String
)
