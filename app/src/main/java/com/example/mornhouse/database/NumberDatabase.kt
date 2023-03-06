package com.example.mornhouse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NumberEntity::class], version = 1, exportSchema = false)
abstract class NumberDatabase : RoomDatabase() {

    abstract fun numberDao(): NumberDao

    companion object {
        @Volatile
        private var INSTANCE: NumberDatabase? = null

        fun getDatabase(context: Context): NumberDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NumberDatabase::class.java,
                    // create const ..todo
                    "numbers_database"
                ).build()
                INSTANCE = instance
                return instance
            }


        }
    }
}