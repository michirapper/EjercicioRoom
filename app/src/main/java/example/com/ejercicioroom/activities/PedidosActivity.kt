package example.com.ejercicioroom.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import example.com.ejercicioroom.R
import example.com.ejercicioroom.adapters.CustomRecyclerAdapter
import example.com.ejercicioroom.database.DataRepository
import example.com.ejercicioroom.model.Item
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class PedidosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)
    }

    fun verPedido(view: View) {
        var editTextCodigo = findViewById<EditText>(R.id.editTextCodPedido)
        var codigoPedido = editTextCodigo.text.toString().toInt()
        var dataRepository = DataRepository(this)
        var numeroClientes = dataRepository.getPedidosOne(codigoPedido)
        val numProductos = numeroClientes.component1().productos.size.toString()
        var FechaPedido = numeroClientes.component1().pedido.fecha.toString()
        val idPedido = numeroClientes.component1().pedido.pedidoId.toString()
//        for (i in 0..numProductos.toInt() - 1) {
//            val producto = numeroClientes.component1().productos[i].nombre.toString()
//            val producto = numeroClientes.component1().productos[i].precio.toString()
//            Toast.makeText(applicationContext, producto, Toast.LENGTH_LONG).show()
//            editTextCodigo.setText(producto)
//        }

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var arrayList = leerLista()
        var customAdapter = CustomRecyclerAdapter(arrayList, this)
        recyclerView.adapter = customAdapter
    }

    fun leerLista(): ArrayList<Item> {
        var arrayRdo = ArrayList<Item>()
        var editTextCodigo = findViewById<EditText>(R.id.editTextCodPedido)
        var codigoPedido = editTextCodigo.text.toString().toInt()
        var dataRepository = DataRepository(this)
        var numeroClientes = dataRepository.getPedidosOne(codigoPedido)
        val numProductos = numeroClientes.component1().productos.size.toString()
        var FechaPedido = numeroClientes.component1().pedido.fecha.toString()
        val idPedido = numeroClientes.component1().pedido.pedidoId.toString()
        for (i in 0..numProductos.toInt() - 1) {
            val productoNombre = numeroClientes.component1().productos[i].nombre.toString()
            val productoPrecio = numeroClientes.component1().productos[i].precio.toString()
            var linea = productoNombre + "#" + productoPrecio
            arrayRdo.add(Item(linea))
        }
//        for (i in 0..numProductos.toInt() - 1) {
//            val productoNombre = numeroClientes.component1().productos[i].nombre.toString()
//            val productoPrecio = numeroClientes.component1().productos[i].precio.toString()
//            var linea = productoNombre + "#" + productoPrecio
//            arrayRdo.add(Item(linea))
//        }
        return arrayRdo
    }
    fun clickProduct(texto : String){
        Toast.makeText(applicationContext, "$texto", Toast.LENGTH_LONG).show()
    }

}