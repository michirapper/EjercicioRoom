package example.com.ejercicioroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import example.com.ejercicioroom.database.Customer

@Dao
interface CustomerDao {
    @Query("SELECT count(*) FROM customer")
    fun getCount(): Int

    @Insert
    fun insertAll(vararg users: Customer)
}