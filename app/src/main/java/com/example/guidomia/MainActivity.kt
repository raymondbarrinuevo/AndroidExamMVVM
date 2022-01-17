package com.example.guidomia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.guidomia.databinding.ActivityMainBinding
import com.example.guidomia.db.Movie
import com.example.guidomia.db.MovieDatabase
import com.example.guidomia.db.MovieRepository
import com.example.guidomia.network.RetrofitService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MovieAdapterInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val retrofitService = RetrofitService.getInstance()
        val dao = MovieDatabase.getInstance(application).movieDao
        val repository = MovieRepository(retrofitService, dao)
        val factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        binding.myViewModel = movieViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        movieViewModel.movieList.observe(this, {
            adapter.setList(it)
        })

        movieViewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        movieViewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
                movieViewModel.getAll().observe(this, Observer {
                    adapter.setList(it)
                    adapter.notifyItemRangeChanged(0, it.size)
                })
            }
        })

        lifecycleScope.launch {
            movieViewModel.getAllMovies()
        }
    }


    private fun initRecyclerView() {
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieRecyclerViewAdapter(this)
        binding.movieRecyclerView.adapter = adapter
        (binding.movieRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
            false
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
        this.finish()
    }
}