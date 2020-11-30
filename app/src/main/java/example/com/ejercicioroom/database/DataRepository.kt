package example.com.ejercicioroom.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val customerDao: CustomerDao? = AppDatabase.getInstance(context)?.customerDao()
    private val pedidoDao: PedidoDao? = AppDatabase.getInstance(context)?.pedidoDao()
    private val productoDao: ProductDao? = AppDatabase.getInstance(context)?.productDao()
    private val pedidoProductoDao: PedidoProductoDao? = AppDatabase.getInstance(context)?.pedidoProductoDao()

    fun insert(customer: Customer):Int {
        if (customerDao != null){
            return InsertAsyncTask(customerDao).execute(customer).get()
        }
        return -1
    }

    fun insert(pedidoProducto: PedidoProducto):Int{
        if (pedidoDao != null && productoDao !=null && pedidoProductoDao!= null) {
            return InsertPedidoProductoAsyncTask(pedidoDao, productoDao, pedidoProductoDao).execute(pedidoProducto).get()
        }
        return -1
    }

    fun getPedidos(): List<PedidoProducto>{
        return GetPedidos(pedidoProductoDao!!).execute().get()
    }
    fun getPedidosOne(idPedido: Int): List<PedidoProducto>{
        return GetPedidosOne(pedidoProductoDao!!, idPedido).execute().get()
    }

    fun getCountCustomer(): Int {
        var getCount = GetCount(customerDao!!).execute().get()
        return getCount
    }

    private class InsertPedidoProductoAsyncTask(private val pedidoDao: PedidoDao, private val productoDao: ProductDao, private val pedidoProductoDao: PedidoProductoDao): AsyncTask<PedidoProducto, Void, Int>(){
        override fun doInBackground(vararg pedidoProductos: PedidoProducto?): Int {
            try{
                for (pedidoProducto in pedidoProductos){
                    if (pedidoProducto !=null){
                        pedidoDao.insertAll(pedidoProducto.pedido)
                        productoDao.insertAll(pedidoProducto.productos)
                        for (producto in pedidoProducto.productos){
                            pedidoProductoDao.insert(PedidoProductoCrossRef(pedidoProducto.pedido.pedidoId, producto.productId))
                        }
                    }
                }

               return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class InsertAsyncTask(private val customerDao: CustomerDao) : AsyncTask<Customer, Void, Int>() {
        override fun doInBackground(vararg customers: Customer?): Int? {
            try {
                for (customer in customers) {
                    if (customer != null) customerDao.insertAll(customer)
                }
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }


    private class GetCount(private val customerDao: CustomerDao) : AsyncTask<Void, Void, Int>() {
        override fun doInBackground(vararg p0: Void?): Int {
            return customerDao.getCount()
        }
    }

    private class GetPedidos(private val pedidoProductoDao: PedidoProductoDao) :AsyncTask<Void, Void, List<PedidoProducto>>(){
        override fun doInBackground(vararg params: Void?): List<PedidoProducto> {
            return pedidoProductoDao.getPedidos()
        }
    }
    private class GetPedidosOne(private val pedidoProductoDao: PedidoProductoDao, private val idPedido: Int) :AsyncTask<Void, Void, List<PedidoProducto>>(){
        override fun doInBackground(vararg params: Void?): List<PedidoProducto> {
            return pedidoProductoDao.getPedidosOne(idPedido)
        }
    }
}