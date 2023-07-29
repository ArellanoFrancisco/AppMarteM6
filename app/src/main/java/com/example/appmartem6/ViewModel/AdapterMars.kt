package com.example.appmartem6.ViewModel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmartem6.Model.Remote.MarsRealState
import com.example.appmartem6.databinding.MarsItemBinding

class AdapterMars : RecyclerView.Adapter<AdapterMars.MarsViewHolder>() {

    // referencia lista de terreno
        private var listMarsItem= listOf<MarsRealState>()

    // para seleccionar un elemento
        val selectedTerrain = MutableLiveData<MarsRealState>()

    //funcion para seleccionar
    fun selectedItem(): LiveData<MarsRealState> = selectedTerrain

    //funcion para actualizar
    @SuppressLint("NotifyDataSetChanged")
    fun update(list: List<MarsRealState>){
        listMarsItem= list
        notifyDataSetChanged()
    }



    inner class MarsViewHolder(private val binding: MarsItemBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener{

        fun bind(mars : MarsRealState){

            Glide.with(binding.imageView).load(mars.img_Src).centerCrop().into(binding.imageView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedTerrain.value = listMarsItem[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        return MarsViewHolder(MarsItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listMarsItem.size

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        val terrain= listMarsItem[position]
        holder.bind(terrain)

    }

}