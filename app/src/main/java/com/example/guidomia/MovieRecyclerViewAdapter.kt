package com.example.guidomia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.guidomia.databinding.ItemMovieBinding
import com.example.guidomia.db.Movie

class MovieRecyclerViewAdapter(val listener: MovieAdapterInterface) :
    RecyclerView.Adapter<MyViewHolder>() {

    var localItems = ArrayList<Movie>()

    private var cars = ArrayList<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cars[position])
        holder.binding.itemView.setOnClickListener {
            listener.onItemClick(cars[position])
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }

    fun setList(movies: List<Movie>) {
        this.cars.clear()
        this.cars.addAll(movies)
        this.localItems = this.cars
    }
}

class MyViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.tvTrackName.text = movie.trackName
        binding.tvGenre.text = movie.primaryGenreName
        binding.tvPrice.text = "${movie.currency} ${movie.trackPrice}"
        Glide.with(binding.itemView.context)
            .load(movie.artworkUrl100)
            .placeholder(R.drawable.placeholder)
            .into(binding.ivCar)
    }

}

interface MovieAdapterInterface {
    fun onItemClick(movie: Movie)
}