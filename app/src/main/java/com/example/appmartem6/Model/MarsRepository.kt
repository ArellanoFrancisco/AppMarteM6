package com.example.appmartem6.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appmartem6.Model.Local.MarsDao
import com.example.appmartem6.Model.Remote.MarsRealState
import com.example.appmartem6.Model.Remote.RetrofitClient

class MarsRepository ( private val marsDao: MarsDao) {

    // variable tipo conexion ocupa la funcion retrofit para conectarse

    private val retrofitClient = RetrofitClient.getRetrofit()

    // hace Referencia al pojo y la respuesta que vamos a recibir
    val dataFromInternet = MutableLiveData<List<MarsRealState>>()


    //vieja confiable

/*    fun fetchDataMars(): LiveData<List<MarsRealState>> {
        Log.d("Repo", "vieja Confiable")
        retrofitClient.fetchMarsData().enqueue(object : retrofit2.Callback<List<MarsRealState>> {
            override fun onResponse(
                call: Call<List<MarsRealState>>,
                response: Response<List<MarsRealState>>
            ) {
                when (response.code()) {
                    in 200..299 -> dataFromInternet.value = response.body()
                    in 300..301 -> Log.d("REPO", "${response.code()} ---${response.errorBody()}")
                    else -> Log.d("REPO", "${response.code()} ---${response.errorBody()}")
                }
            }
            override fun onFailure(call: Call<List<MarsRealState>>, t: Throwable) {
                Log.e("Error", " ${t.message}")
            }

        })

        return dataFromInternet
    }*/

    suspend fun fetchDataFromInternetCoroutines() {
        try {
            val response = retrofitClient.fetchMarsDataCoroutines()
            when (response.code()) {
                in 200..299 -> response.body().let {
                    if (it != null) {
                        marsDao.insertAllMars(it)
                    }
                }
                in 300..301 -> Log.d("REPO", "${response.code()}  ---${response.errorBody()}")
                else -> Log.d(
                    "REPO", "${response.code()} --- ${response.errorBody().toString()}"
                )
            }
        } catch (t: Throwable) {
            Log.e("REPO", "${t.message}")
        }
    }

/*    fun getTerrainByid(id :String): LiveData<MarsRealState>{
        return getTerrainByid(id)
    }*/

    // listado de terrenos
    val listAllMars: LiveData<List<MarsRealState>> = marsDao.getAllMars()
/*

    suspend fun insertMars(mars: MarsRealState) {
        marsDao.insertMars(mars)}


    suspend fun updateMars(mars: MarsRealState) {
        marsDao.updateMars(mars)

    }

    // elimina terrenos

    suspend fun deleteall(mars:MarsRealState) {
        marsDao.deleteMars(mars)
    }

    //traer terreno por id

    fun getMarsById(id: String): LiveData<MarsRealState> {
        return marsDao.getMarsById(id)
    }
*/


}

