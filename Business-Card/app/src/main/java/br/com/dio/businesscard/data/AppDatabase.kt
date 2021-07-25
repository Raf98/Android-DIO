package br.com.dio.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessCardDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /*private val MIGRATION_1_2 : Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE IF EXISTS BusinessCard");
            }
        }*/

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                )
                    //.addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}