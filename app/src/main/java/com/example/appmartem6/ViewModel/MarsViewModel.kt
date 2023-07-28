package com.example.appmartem6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appmartem6.Model.Local.MarsDataBase
import com.example.appmartem6.Model.MarsRepository
import com.example.appmartem6.Model.Remote.MarsRealState
import kotlinx.coroutines.launch

class MarsViewModel(application: Application):AndroidViewModel(application) {



    private val repository: MarsRepository


//    var liveDatafromInternet: LiveData<List<MarsRealState>>
    // parte 2
    lateinit var liveDatafromInternet: LiveData<List<MarsRealState>>
    val allMars: LiveData<List<MarsRealState>>

    init {
        // llamando la instancia el dao en la base de datos
        val MarsDao = MarsDataBase.getDataBase(application).getMarsDao()
        repository = MarsRepository(MarsDao)
        //parte 1 sin Coroutines
/*         liveDatafromInternet = repository.fetchDataMars()
         allMars = repository.listAllMars*/
        // fin parte 1

        // parte 2 con Coroutines
        viewModelScope.launch {
            repository.fetchDataFromInternetCoroutines()
        }
        // fin parte 2

        allMars = repository.listAllMars
        liveDatafromInternet = repository.dataFromInternet
    }

    //******************************Estos métodos son con el dao****************/
    // funcion para seleccionar
    // guardar la seleccion en una mutableLiveDATA

    private var selectedMarsTerrains: MutableLiveData<MarsRealState> = MutableLiveData()

    fun selected(marsTerrains: MarsRealState) {
        selectedMarsTerrains.value = marsTerrains
    }

    fun selectedItem(): LiveData<MarsRealState> = selectedMarsTerrains


    fun insertMars(mars: MarsRealState) = viewModelScope.launch {

        repository.insertMars(mars)
    }

    fun updateMars(mars: MarsRealState) = viewModelScope.launch {
        repository.updateMars(mars)
    }
    fun deleteMars(mars: MarsRealState) = viewModelScope.launch {
        repository.deleteMars(mars)

    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteall()

    }

    fun getmarsByType(type:String): LiveData<MarsRealState>{
        return  repository.getMarsByType(type)
    }

    fun getMarsById(id:String): LiveData<MarsRealState>{
        return  repository.getMarsById(id)
    }




}