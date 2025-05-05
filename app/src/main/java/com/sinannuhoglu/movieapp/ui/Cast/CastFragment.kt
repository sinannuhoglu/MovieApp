package com.sinannuhoglu.movieapp.ui.Cast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sinannuhoglu.movieapp.databinding.FragmentCastBinding
import com.sinannuhoglu.movieapp.util.loadImage

class CastFragment : Fragment() {
    private var _binding: FragmentCastBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CastFragmentArgs>()
    private val viewModel by viewModels<CastViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCastBinding.inflate(inflater, container, false)

        observeEvents()
        setupClickListeners()
        viewModel.getMovieCredits(movieId = args.movieId)

        return binding.root
    }

    private fun observeEvents() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBarCast.isVisible = it
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textViewErrorCast.text = it
            binding.textViewErrorCast.isVisible = true
        }
        viewModel.castList.observe(viewLifecycleOwner) { castList ->
            val cast = castList?.find { it?.id == args.castId }

            if (cast != null) {
                binding.imageViewCast.loadImage(cast.profilePath)
                binding.textViewCastTitle.text = cast.name
                binding.textViewCastCharacter.text = cast.character ?: "-"
            } else {
                binding.textViewErrorCast.text = "Oyuncu bulunamadı"
                binding.textViewErrorCast.isVisible = true
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