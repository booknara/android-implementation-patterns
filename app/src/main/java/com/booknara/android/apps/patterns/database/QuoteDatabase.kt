package com.booknara.android.apps.patterns.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.booknara.android.apps.patterns.database.dao.QuoteDao
import com.booknara.android.apps.patterns.database.entity.Quote

@Database(entities = arrayOf(Quote::class), version = 1, exportSchema = true)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        private const val DB_FILE_NAME = "quotes.db"

        @Volatile
        private var instance: QuoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        QuoteDatabase::class.java,
                        DB_FILE_NAME
                ).build()
    }
}