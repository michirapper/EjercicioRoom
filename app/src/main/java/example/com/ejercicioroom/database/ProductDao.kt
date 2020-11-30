package example.com.ejercicioroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import example.com.ejercicioroom.database.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product")
    fun getAll(): List<Product>

    @Insert
    fun insertAll(products: List<Product>)
    @Insert
    fun insertAll(vararg products: Product)

}