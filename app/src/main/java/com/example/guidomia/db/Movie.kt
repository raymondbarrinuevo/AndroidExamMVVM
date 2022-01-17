package com.example.guidomia.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_data_table")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "trackId")
    var trackId: Int,

    @ColumnInfo(name = "wrapperType")
    var wrapperType: String,

    @ColumnInfo(name = "kind")
    var kind: String,

    @ColumnInfo(name = "collectionId")
    var collectionId: Int,

    @ColumnInfo(name = "artistName")
    var artistName: String,

    @ColumnInfo(name = "collectionName")
    var collectionName: String,

    @ColumnInfo(name = "trackName")
    var trackName: String,

    @ColumnInfo(name = "collectionCensoredName")
    var collectionCensoredName: String,

    @ColumnInfo(name = "trackCensoredName")
    var trackCensoredName: String,

    @ColumnInfo(name = "collectionArtistId")
    var collectionArtistId: Int,

    @ColumnInfo(name = "collectionArtistViewUrl")
    var collectionArtistViewUrl: String,

    @ColumnInfo(name = "collectionViewUrl")
    var collectionViewUrl: String,

    @ColumnInfo(name = "trackViewUrl")
    var trackViewUrl: String,

    @ColumnInfo(name = "previewUrl")
    var previewUrl: String,

    @ColumnInfo(name = "artworkUrl30")
    var artworkUrl30: String,

    @ColumnInfo(name = "artworkUrl60")
    var artworkUrl60: String,

    @ColumnInfo(name = "artworkUrl100")
    var artworkUrl100: String,

    @ColumnInfo(name = "collectionPrice")
    var collectionPrice: Float,

    @ColumnInfo(name = "trackPrice")
    var trackPrice: Float,

    @ColumnInfo(name = "trackRentalPrice")
    var trackRentalPrice: Float,

    @ColumnInfo(name = "collectionHdPrice")
    var collectionHdPrice: Float,

    @ColumnInfo(name = "trackHdPrice")
    var trackHdPrice: Float,

    @ColumnInfo(name = "trackHdRentalPrice")
    var trackHdRentalPrice: Float,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "collectionExplicitness")
    var collectionExplicitness: String,

    @ColumnInfo(name = "trackExplicitness")
    var trackExplicitness: String,

    @ColumnInfo(name = "discCount")
    var discCount: Int,

    @ColumnInfo(name = "discNumber")
    var discNumber: Int,

    @ColumnInfo(name = "trackCount")
    var trackCount: Int,

    @ColumnInfo(name = "trackNumber")
    var trackNumber: Int,

    @ColumnInfo(name = "trackTimeMillis")
    var trackTimeMillis: Int,

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "currency")
    var currency: String,

    @ColumnInfo(name = "primaryGenreName")
    var primaryGenreName: String,

    @ColumnInfo(name = "contentAdvisoryRating")
    var contentAdvisoryRating: String,

    @ColumnInfo(name = "shortDescription")
    var shortDescription: String = "",

    @ColumnInfo(name = "longDescription")
    var longDescription: String,

    @ColumnInfo(name = "hasITunesExtras")
    var hasITunesExtras: Boolean

) : Serializable

data class Result(var resultCount: Int, var results: List<Movie>)
