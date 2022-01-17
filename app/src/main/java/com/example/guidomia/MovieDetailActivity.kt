package com.example.guidomia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.guidomia.databinding.ActivityMovieDetailBinding
import com.example.guidomia.db.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra("movie") as? Movie

        binding.tvTrackName.text = movie?.trackName
        binding.tvGenre.text = movie?.primaryGenreName
        binding.tvPrice.text = "${movie?.currency} ${movie?.trackPrice}"
        Glide.with(binding.itemView.context)
            .load(movie?.artworkUrl100)
            .placeholder(R.drawable.placeholder)
            .into(binding.ivCar)
        binding.tvLongDescription.text = movie?.longDescription

        binding.btnBack.setOnClickListener {
            val intent = Intent(this@MovieDetailActivity, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}