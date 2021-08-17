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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeDataViewModel by viewModels()
    lateinit var mAdapter: RoomInfoAdapter
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mAdapter = RoomInfoAdapter(viewModel)
        binding.rvHome.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }

        getRooms()
        return binding.root
    }

    private fun getRooms() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getRooms().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

}