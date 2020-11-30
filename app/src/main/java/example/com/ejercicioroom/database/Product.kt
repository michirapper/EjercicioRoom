package example.com.ejercicioroom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val productId: Int,
    val nombre: String?,
    val precio: Int?
)