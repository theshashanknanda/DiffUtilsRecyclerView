package com.example.kotlinnotesapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinnotesapp.R
import com.example.kotlinnotesapp.adapters.NoteAdapter
import com.example.kotlinnotesapp.adapters.NoteClickListener
import com.example.kotlinnotesapp.data.Note
import com.example.kotlinnotesapp.databinding.FragmentHomeBinding
import com.example.kotlinnotesapp.viewmodel.NoteViewModel
import com.example.kotlinnotesapp.viewmodel.NoteViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding: FragmentHomeBinding
        get() = _binding

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // code
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_insertFragment)
        }

        viewModel = ViewModelProvider(this, NoteViewModelFactory(requireContext().applicationContext)).get(NoteViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        var adapter = NoteAdapter(mutableListOf())
        viewModel.notes.observe(requireActivity()){
            adapter.addNewData(it)

            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        adapter.setOnClickListener(object : NoteClickListener{
            override fun onClick(id: Int) {
                var bundle = bundleOf("id" to id)
                findNavController().navigate(R.id.action_homeFragment_to_updateFragment, bundle)
            }

        })
    }
}