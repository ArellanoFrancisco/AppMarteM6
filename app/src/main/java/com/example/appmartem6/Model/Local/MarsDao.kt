package com.example.appmartem6.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appmartem6.Model.Remote.MarsRealState

@Dao
interface MarsDao {
    //insertar un terreno
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMars(mars: MarsRealState)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMars(mars: List<MarsRealState>)

    @Update
    suspend fun updateMars (mars: MarsRealState)

    @Delete
    suspend fun deleteMars (mars: MarsRealState)


    // traer todos los  terrenos
    @Query("SELECT * FROM Mars_Table ORDER BY id DESC")
    fun getAllMars(): LiveData<List<MarsRealState>>
    // traer por tipo venta o alquiler

    @Query("SELECT * FROM Mars_Table Where id =:id")
    fun getMarsById(id: String): LiveData<MarsRealState>


}