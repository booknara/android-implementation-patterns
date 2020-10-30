package com.booknara.android.apps.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = 0

@Entity(tableName = "quote")
data class Quote(
        @ColumnInfo(name = "_internalId")
        val _internalId: String,

        @ColumnInfo(name = "quote_id")
        val id: String,
        val title: String,
        val author: String
) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var primaryId: Int = ID
}