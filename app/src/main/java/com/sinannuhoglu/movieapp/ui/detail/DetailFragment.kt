package com.sinannuhoglu.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sinannuhoglu.movieapp.MainActivity
import com.sinannuhoglu.movieapp.databinding.FragmentDetailBinding
import com.sinannuhoglu.movieapp.model.Cast
import com.sinannuhoglu.movieapp.util.GenreMapper
import com.sinannuhoglu.movieapp.util.loadImage

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private lateinit var detailAdapter: DetailAdapter
    private var allCast: List<Cast?> = emptyList()

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.getMovieDetail(movieId = args.movieId)
        viewModel.getMovieCredits(movieId = args.movieId)

        observeEvents()
        setupClickListeners()

        return binding.root
    }

    private fun observeEvents() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarDetail.isVisible = it
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textViewErrorDetail.text = it
            binding.textViewErrorDetail.isVisible = true
        }
        viewModel.movieResponse.observe(viewLifecycleOwner) { movie ->
            binding.imageViewDetail.loadImage(movie.backdropPath)

            val formattedVote = String.format("%.1f", movie.voteAverage)
            binding.textViewDetailVote.text = formattedVote

            binding.textViewDetailReleaseDate.text = movie.releaseDate

            val durationWithUnit = "${movie.runtime} minute"
            binding.textViewDetailDuration.text = durationWithUnit

            binding.textViewDetailOverview.text = movie.overview
            binding.textViewMovieTitle.text = movie.title

            val genreIds = movie.genres?.mapNotNull { it.id }
            val genreText = GenreMapper.mapGenres(genreIds)
            binding.textViewDetailGenre.text = genreText

            (requireActivity() as MainActivity).supportActionBar?.title = movie.title
        }

        viewModel.castList.observe(viewLifecycleOwner) { list ->
            if(list.isNullOrEmpty()) {
                binding.textViewErrorDetail.text = "There is any movie"
                binding.textViewErrorDetail.isVisible = true
            } else {
                binding.textViewErrorDetail.isVisible = false
                allCast = list

                detailAdapter = DetailAdapter(list, object : DetailAdapterClickListener {
                    override fun onMovieClicked(cast: Cast?) {
                        cast?.id?.let { castId ->
                            val action = DetailFragmentDirections
                                .actionDetailFragmentToCastFragment(
                                    castId = castId,
                                    movieId = args.movieId // DetailFragment'a gelen movieId
                                )
                            findNavController().navigate(action)
                        }
                    }
                })
                binding.castRecyclerView.adapter = detailAdapter
            }
        }
    }

    private fun setupClickListeners() {
        binding.imageViewBackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}