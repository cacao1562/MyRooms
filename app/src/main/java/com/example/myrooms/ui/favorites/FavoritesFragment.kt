package com.example.myrooms.ui.favorites

import android.os.Bundle
import android.util.Log
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

        binding.rvFavorites.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }

        viewModel.favoritesList.observe(viewLifecycleOwner, Observer {
            Log.d("aaa", "favoritesData = $it")
            mAdapter.submitList(it)
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRooms()
    }
}