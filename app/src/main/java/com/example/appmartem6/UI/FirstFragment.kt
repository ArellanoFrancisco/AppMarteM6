package com.example.appmartem6.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appmartem6.R
import com.example.appmartem6.ViewModel.AdapterMars
import com.example.appmartem6.ViewModel.MarsViewModel
import com.example.appmartem6.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : MarsViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterMars()
        binding.RVTerrains.adapter = adapter
        binding.RVTerrains.layoutManager= GridLayoutManager(context,2)





       viewModel.liveDatafromInternet.observe(viewLifecycleOwner) {
           it?.let {
               adapter.update(it)
               Log.d("Listado", it.toString())
           }
       }


        viewModel.allMars.observe(viewLifecycleOwner) {
            adapter.update(it)
            Log.d("Listado", it.toString())


        }

        adapter.selectedItem().observe(viewLifecycleOwner) {
            it.let {

                viewModel.selected(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}