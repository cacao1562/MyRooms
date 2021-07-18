package com.example.myrooms.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myrooms.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()
    lateinit var mAdapter: RoomInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        mAdapter = RoomInfoAdapter(viewModel)
        binding.rvHome.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
        lifecycleScope.launchWhenCreated {
            viewModel.roomsProduct.collectLatest {
                mAdapter.submitData(it)
            }
        }
        return binding.root
    }
}