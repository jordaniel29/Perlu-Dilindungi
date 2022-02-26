package com.example.perludilindungi.ui.faskes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.perludilindungi.R
import com.example.perludilindungi.databinding.FragmentFaskesBinding
import com.example.perludilindungi.repository.Repository

class FaskesFragment : Fragment() {

    private var _binding: FragmentFaskesBinding? = null
    private lateinit var viewModel: FaskesViewModel
    private val provinces = ArrayList<String>()
    private val cities = ArrayList<String>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        viewModel.getProvince()
        viewModel.provResponse.observe(viewLifecycleOwner) { response ->
            provinces.addAll(response.results.map { it.key }
                .toList())
            val arrayAdapter = ArrayAdapter(
                requireContext(), R.layout
                    .text_item, provinces
            )
            binding.autoCompleteTextViewProvince.setAdapter(arrayAdapter)
        }

        binding.autoCompleteTextViewProvince.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.getCity(provinces[i])
            viewModel.cityResponse.observe(viewLifecycleOwner) { response ->
                cities.clear()
                cities.addAll(response.results.map { it.key }
                    .toList())
                val arrayAdapter = ArrayAdapter(
                    requireContext(), R.layout
                        .text_item, cities
                )
                Log.d(
                    "Response", response.results.toString()
                )
                binding.autoCompleteTextViewCity.setAdapter(arrayAdapter)
                binding.autoCompleteTextViewCity.text.clear()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = Repository()
        val viewModelFactory = FaskesViewModelFactory(repository)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            )[FaskesViewModel::class.java]
        _binding = FragmentFaskesBinding.inflate(inflater, container, false)

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}