package com.example.appmartem6.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmartem6.R
import com.example.appmartem6.ViewModel.MarsViewModel
import com.example.appmartem6.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel : MarsViewModel by activityViewModels()
    var idMars: String= ""

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMars = it.getString("id","")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem().observe(viewLifecycleOwner) {
            it?.let {
                Glide.with(binding.imgSelected).load(it.img_Src).centerCrop()
                    .into(binding.imgSelected)
                binding.priceSelected.text = it.price.toString()
                binding.typeSelected.text = it.type
            }
        }

        binding.btnvolver.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }
}