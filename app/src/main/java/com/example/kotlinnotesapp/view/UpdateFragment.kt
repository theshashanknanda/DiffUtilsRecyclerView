package com.example.kotlinnotesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlinnotesapp.R
import com.example.kotlinnotesapp.data.Note
import com.example.kotlinnotesapp.databinding.FragmentUpdateBinding
import com.example.kotlinnotesapp.viewmodel.NoteViewModel
import com.example.kotlinnotesapp.viewmodel.NoteViewModelFactory

class UpdateFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateBinding
    private val binding: FragmentUpdateBinding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // code
        var viewModel = ViewModelProvider(requireActivity(), NoteViewModelFactory(requireContext())).get(NoteViewModel::class.java)

        // find current note
        var note: Note? = null

        var id = arguments?.getInt("id")
        viewModel.notes.observe(requireActivity()){
            var filteredList = it.filter { it.id == id }
            note = filteredList[0]

            binding.title.setText(note?.title)
            binding.description.setText(note?.description)
        }

        binding.btn.setOnClickListener {
            note?.title = binding.title.text.toString()
            note?.description = binding.description.text.toString()
            viewModel.update(note!!)

            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
    }
}
