package com.project.task.features.presentation.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.task.databinding.FragmentDataShowBinding
import com.project.task.features.presentation.UserDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDataFragment : Fragment() {

    private var _binding: FragmentDataShowBinding? = null
    private val binding get() = _binding!!

    private val userDataViewModel: UserDataViewModel by viewModels()
    private lateinit var userDataAdapter: UserDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDataShowBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    private fun initAdapter() {
        userDataAdapter = UserDataAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerView.adapter = userDataAdapter

        userDataViewModel.getAllDat().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.emptyData.visibility = View.VISIBLE
            } else {
                binding.emptyData.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                userDataAdapter.submitList(it.toMutableList())
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}