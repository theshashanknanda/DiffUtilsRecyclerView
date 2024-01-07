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
import com.example.kotlinnotesapp.databinding.FragmentInsertBinding
import com.example.kotlinnotesapp.viewmodel.NoteViewModel
import com.example.kotlinnotesapp.viewmodel.NoteViewModelFactory

class InsertFragment : Fragment() {
    private lateinit var _binding: FragmentInsertBinding
    private val binding: FragmentInsertBinding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var viewmodel = ViewModelProvider(this, NoteViewModelFactory(requireContext().applicationContext)).get(NoteViewModel::class.java)

        // code
        binding.btn.setOnClickListener{
            var title = binding.title.text.toString()
            var description = binding.description.text.toString()

            var note = Note(title = title, description = description)

            viewmodel.insert(note)
            findNavController().navigate(R.id.action_insertFragment_to_homeFragment)
        }
    }


}
