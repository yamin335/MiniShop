package com.mallzhub.mallowner.local_db.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mallzhub.mallowner.models.Product

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "product") val product: Product,
    @ColumnInfo(name = "quantity") val quantity: Int?
)