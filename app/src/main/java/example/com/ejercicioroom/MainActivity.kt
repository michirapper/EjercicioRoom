package example.com.ejercicioroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import example.com.ejercicioroom.activities.PedidosActivity
import example.com.ejercicioroom.activities.RegisterActivity
import example.com.ejercicioroom.database.DataRepository
import example.com.ejercicioroom.database.Pedido
import example.com.ejercicioroom.database.PedidoProducto
import example.com.ejercicioroom.database.Product


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rellenarPedidos()

    }

    override fun onResume() {
        super.onResume()

        var dataRepository = DataRepository(this)
        var numeroClientes = dataRepository.getCountCustomer()

        var buttonListar = findViewById<Button>(R.id.buttonListar)
        buttonListar.text = "Listar (" + numeroClientes.toString() + ")"
    }

    fun onRegistrar(view: View) {
        var intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun onListar(view: View) {

    }

    private fun rellenarPedidos(){
        var producto1 = Product(1, "Producto1", 10)
        var producto2 = Product(2, "Producto2", 12)
        var producto3 = Product(3, "Producto3", 13)
        var producto4 = Product(4, "Producto4", 14)
        var producto5 = Product(5, "Producto5", 15)

        var listaProductos1 = ArrayList<Product>()
        listaProductos1.add(producto1)
        listaProductos1.add(producto2)

        var listaProductos2 = ArrayList<Product>()
        listaProductos2.add(producto3)
        listaProductos2.add(producto4)
        listaProductos2.add(producto5)

        var pedido1 = Pedido(1, 1)
        var pedido2 = Pedido(2, 2)

        var pedidoProducto1 = PedidoProducto(pedido1, listaProductos1)
        var pedidoProducto2 = PedidoProducto(pedido2, listaProductos2)

        var dataRepository = DataRepository(this)
        dataRepository.insert(pedidoProducto1)
        dataRepository.insert(pedidoProducto2)

        var pedidosGuardados = dataRepository.getPedidos()
        val i=0
    }

    fun onPedidos(view: View) {
        var intent = Intent(this, PedidosActivity::class.java)
        startActivity(intent)
    }
}