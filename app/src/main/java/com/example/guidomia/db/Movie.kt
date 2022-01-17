package com.example.guidomia.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_data_table")
data class Movie(

    @PrimaryKey
    @ColumnInfo(name = "trackId")
    var trackId: Int,

    @ColumnInfo(name = "kind")
    var kind: String,

    @ColumnInfo(name = "trackName")
    var trackName: String,

    @ColumnInfo(name = "artworkUrl100")
    var artworkUrl100: String,


    @ColumnInfo(name = "trackPrice")
    var trackPrice: Float,

    @ColumnInfo(name = "currency")
    var currency: String,

    @ColumnInfo(name = "primaryGenreName")
    var primaryGenreName: String,

    @ColumnInfo(name = "longDescription")
    var longDescription: String

) : Serializable

data class Result(var resultCount: Int, var results: List<Movie>)
