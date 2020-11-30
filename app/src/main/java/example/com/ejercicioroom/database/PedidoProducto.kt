package example.com.ejercicioroom.database

import androidx.room.*

data class PedidoProducto (
    @Embedded var pedido: Pedido,

    @Relation(
        parentColumn = "pedidoId",
        entity = Product::class,
        entityColumn = "productId",
        associateBy = Junction(value = PedidoProductoCrossRef::class,
            parentColumn = "pedidoId",
            entityColumn = "productId")
    )

    var productos: List<Product>
    )

@Entity(primaryKeys = ["pedidoId", "productId"])
data class PedidoProductoCrossRef(
    val pedidoId: Int,
    val productId: Int
)