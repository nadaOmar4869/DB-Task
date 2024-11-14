package com.project.task.features.presentation.save

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project.task.R
import com.project.task.databinding.FragmentDataSaveBinding
import com.project.task.databinding.LayoutTextFiledBinding
import com.project.task.features.presentation.UserDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaveDataFragment : Fragment() {
    private val userDataViewModel: UserDataViewModel by viewModels()

    private var _binding: FragmentDataSaveBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDataSaveBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = userDataViewModel

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setGenderList()
        binding.apply {
            showAll.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            save.setOnClickListener {
                userDataViewModel.insertData {
                    // Clear input fields
                    nameLayout.editText.text = null
                    jobTitleLayout.editText.text = null
                    ageLayout.editText.text = null
                    genderSpinner.autoCompleteTextView.text = null

                    Toast.makeText(
                        this@SaveDataFragment.requireContext(),
                        getString(R.string.data_saved_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }


    private fun setGenderList() {

        val adapter = ArrayAdapter(
            this@SaveDataFragment.requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            resources.getStringArray(R.array.gender)
        )

        binding.genderSpinner.autoCompleteTextView.setAdapter(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}