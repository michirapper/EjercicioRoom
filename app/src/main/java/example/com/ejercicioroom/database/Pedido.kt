package example.com.ejercicioroom.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Pedido (
    @PrimaryKey val pedidoId: Int,
    val fecha: Int
    )