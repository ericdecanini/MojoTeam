package com.ericthecoder.mojoteam.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [OfflineTeamMember::class], version = 1, exportSchema = false)
abstract class TeamDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object {

        @Volatile
        private var INSTANCE: TeamDatabase? = null

        fun getDatabase(context: Context): TeamDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    "todo_lists"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
