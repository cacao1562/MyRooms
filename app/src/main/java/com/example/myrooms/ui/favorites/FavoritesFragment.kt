package com.example.myrooms.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myrooms.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment: Fragment() {

    lateinit var binding: FragmentFavoritesBinding

    private val viewModel: FavoritesViewModel by viewModels()

    lateinit var mAdapter: FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)

        mAdapter = FavoritesAdapter(viewModel)
        viewModel.favoritesData.observe(viewLifecycleOwner, Observer { 

        })
        binding.rvFavorites.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        return binding.root
    }
}