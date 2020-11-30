package example.com.ejercicioroom.database

import androidx.room.Dao
import androidx.room.Insert
import example.com.ejercicioroom.database.Pedido

@Dao
interface PedidoDao {
    @Insert
    fun insertAll(vararg pedido: Pedido)
}