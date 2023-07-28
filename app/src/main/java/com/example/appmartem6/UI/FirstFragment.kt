package com.example.appmartem6.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appmartem6.R
import com.example.appmartem6.ViewModel.AdapterMars
import com.example.appmartem6.ViewModel.MarsViewModel
import com.example.appmartem6.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : MarsViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AdapterMars()
        binding.RVTerrains.adapter = adapter
        binding.RVTerrains.layoutManager= GridLayoutManager(context,2)





/*
        viewModel.liveDatafromInternet.observe(viewLifecycleOwner, Observer {
          it?.let{
              adapter.update(it)
          }
      })
*/


        viewModel.allMars.observe(viewLifecycleOwner, Observer {
            adapter.update(it)

//           listaMars->
//                binding.textviewFirst.text = listaMars.toString()

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}