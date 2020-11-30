package example.com.ejercicioroom.database

import androidx.room.*
import example.com.ejercicioroom.database.PedidoProducto
import example.com.ejercicioroom.database.PedidoProductoCrossRef

@Dao
interface PedidoProductoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: PedidoProductoCrossRef)

    @Transaction
    @Query("SELECT * FROM Pedido")
    fun getPedidos(): List<PedidoProducto>

    @Transaction
    @Query("SELECT * FROM Pedido WHERE pedidoId = :idPedido")
    fun getPedidosOne(idPedido: Int): List<PedidoProducto>
}