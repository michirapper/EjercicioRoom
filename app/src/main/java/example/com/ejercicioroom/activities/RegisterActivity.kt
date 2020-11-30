package example.com.ejercicioroom.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import example.com.ejercicioroom.database.Customer
import example.com.ejercicioroom.database.DataRepository
import example.com.ejercicioroom.R


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun onInsertar(view: View) {
        var editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        var editTextApellido = findViewById<EditText>(R.id.editTextApellido)
        var editTextCodigo = findViewById<EditText>(R.id.editTextCodigo)

        var customer = Customer(editTextCodigo.text.toString().toInt(), editTextNombre.text.toString(), editTextApellido.text.toString())

        var rdo = DataRepository(this).insert(customer)
        if (rdo == 0) {
            Toast.makeText(this, "Insertado correctamente", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Código repetido", Toast.LENGTH_LONG).show()
        }
    }
}