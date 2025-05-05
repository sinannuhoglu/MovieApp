package com.sinannuhoglu.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sinannuhoglu.movieapp.databinding.FragmentHomeBinding
import com.sinannuhoglu.movieapp.model.MovieItem

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> ()
    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter
    private lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter
    private var allNowPlayingMovies: List<MovieItem?> = emptyList()
    private var allUpcomingMovies: List<MovieItem?> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        observeEvents()

        return binding.root
    }

    private fun observeEvents() {
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            binding.textViewHomeError.text = error
            binding.textViewHomeError.isVisible = true
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.isVisible = loading
        }
        viewModel.nowPlayingList.observe(viewLifecycleOwner) { list ->
            if(list.isNullOrEmpty()) {
                binding.textViewHomeError.text = "There is any movie"
                binding.textViewHomeError.isVisible = true
            } else {
                binding.textViewHomeError.isVisible = false

                allNowPlayingMovies = list
                setupSearchView()

                nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(list, object : MovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        movieId?.let {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.homeRecyclerView.adapter = nowPlayingMoviesAdapter
            }
        }

        viewModel.upcomingMovieList.observe(viewLifecycleOwner) { list ->
            if(list.isNullOrEmpty()) {
                binding.textViewHomeError.text = "There is any movie"
                binding.textViewHomeError.isVisible = true
            } else {
                binding.textViewHomeError.isVisible = false

                allUpcomingMovies = list
                setupSearchView()

                upcomingMoviesAdapter = UpcomingMoviesAdapter(list, object : UpcomingMovieClickListener {
                    override fun onMovieClicked(movieId: Int?) {
                        movieId?.let {
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.upcomingMovieRecyclerView.adapter = upcomingMoviesAdapter

            }
        }
    }

    private fun setupSearchView() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterMovies(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterMovies(newText)
                return true
            }
        })
    }

    private fun filterMovies(query: String?) {
        val filteredNowPlaying = if (query.isNullOrBlank()) {
            allNowPlayingMovies
        } else {
            allNowPlayingMovies.filter {
                it?.title?.contains(query.trim(), ignoreCase = true) == true
            }
        }

        val filteredUpcoming = if (query.isNullOrBlank()) {
            allUpcomingMovies
        } else {
            allUpcomingMovies.filter {
                it?.title?.contains(query.trim(), ignoreCase = true) == true
            }
        }

        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(filteredNowPlaying, object : MovieClickListener {
            override fun onMovieClicked(movieId: Int?) {
                movieId?.let {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    findNavController().navigate(action)
                }
            }
        })
        binding.homeRecyclerView.adapter = nowPlayingMoviesAdapter

        upcomingMoviesAdapter = UpcomingMoviesAdapter(filteredUpcoming, object : UpcomingMovieClickListener {
            override fun onMovieClicked(movieId: Int?) {
                movieId?.let {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                    findNavController().navigate(action)
                }
            }
        })
        binding.upcomingMovieRecyclerView.adapter = upcomingMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}