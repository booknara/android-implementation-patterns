package com.booknara.android.apps.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.booknara.android.apps.database.entity.ID
import com.booknara.android.apps.database.entity.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateQuote(quote: Quote)

    @Query("SELECT * from quote where id = $ID")
    fun getQuote(): Flow<Quote>
}