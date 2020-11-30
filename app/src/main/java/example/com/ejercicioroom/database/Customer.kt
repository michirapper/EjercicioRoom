package example.com.ejercicioroom.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val nombre: String?,
    @ColumnInfo(name = "last_name") val apellido: String?
)