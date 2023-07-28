package com.example.appmartem6.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appmartem6.Model.Remote.MarsRealState


@Database(entities = [MarsRealState::class], version = 1)
abstract class MarsDataBase: RoomDatabase() {


    abstract fun getMarsDao(): MarsDao

    // NOSE PUEDE ISNTANCIAR ES ABSTRACTO
    // COMPANION OBJECT EXPONE UN OBJETO SIN INSTANCIAR LA CLASE
    companion object {
        // ESTA VARIABLE ESTE SIEMPRE DISPONIBLE
        @Volatile
        private var INSTANCE: MarsDataBase? = null

        // MAIN THREAD
        // BACK THREAD HILO SECUNDARIOS VOLATILE HACE QUE SE EJECUTE DONDE ESTE DISPONIBLE
        // TAREAS ASINCRONAS


        // CONTEXTO DONDE ESTAMOS EJECUTANDO LOS PROCESOS
        // MUCHAS FORMAS DE EJECUTAR EL CONTEXTO
        fun getDataBase(context: Context): MarsDataBase {

            val tempInstance = INSTANCE
            // ES DISTINTO A NULL
            if (tempInstance != null) {

                return tempInstance
            }

            // LLAMA A UN METODO Y LO SINCRONIZA PARA INSTANCIAR
            synchronized(this) {
                // CLASE DE ROOM ES EL CREADOR DE LA INSTANCIA DE LA BASE DE DATOS
                val instance = Room.databaseBuilder(
                    // la base de datos sea una para toda la app
                    context.applicationContext,
                    // NOMBRE DEL ARCHIVO QUE CONTIENE LA BASE DE DATO
                    MarsDataBase::class.java,
                    "MarsRealState"
                )
                    .build()
                INSTANCE = instance
                return instance

            }

        }
    }

}