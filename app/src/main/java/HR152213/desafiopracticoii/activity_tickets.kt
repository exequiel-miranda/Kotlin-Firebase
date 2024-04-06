package HR152213.desafiopracticoii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class activity_tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val database = FirebaseDatabase.getInstance()
        val referencia = database.getReference("Tickets")

        val txtNumero = findViewById<EditText>(R.id.txtNumeroTicket)
        val txtTitulo = findViewById<EditText>(R.id.txtTituloTicket)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcionTicket)
        val txtDepartamento = findViewById<EditText>(R.id.txtDepartamentoUsuario)
        val txtAutor = findViewById<EditText>(R.id.txtAutorTicket)
        val txtEmail = findViewById<EditText>(R.id.txtEmailAutor)
        val txtFechaCreacion = findViewById<EditText>(R.id.txtFechaCreacionTicket)
        val txtEstado = findViewById<EditText>(R.id.txtEstadoTicket)
        val txtFechaFinalizacion = findViewById<EditText>(R.id.txtFechaFinalizacionTicket)
        val btnGuardarTicket = findViewById<Button>(R.id.btnGuardarTicket)
        val btnListado = findViewById<Button>(R.id.btnVerListadoTickets)


        btnGuardarTicket.setOnClickListener {
            val ticketNuevo = DatosDelTicket("${txtNumero.text}", "${txtTitulo.text}", "${txtDescripcion.text}", "${txtDepartamento.text}", "${txtAutor.text}", "${txtEmail.text}", "${txtFechaCreacion.text}", "${txtEstado.text}", "${txtFechaFinalizacion.text}")
            referencia.push().setValue(ticketNuevo)
            Toast.makeText(this, "Ticket Agregado!", Toast.LENGTH_LONG).show()
        }

        btnListado.setOnClickListener {
            val activityListadoTickets = Intent(this, activity_listado_tickets::class.java)
            startActivity(activityListadoTickets)
        }

    }
}