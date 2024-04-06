package HR152213.desafiopracticoii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase

class activity_tickets : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        val database = FirebaseDatabase.getInstance()
        val referencia = database.getReference("Tickets")

        val txtNumero = findViewById<EditText>(R.id.txtNumeroTicket)
        val txtNumeroLayout = findViewById<TextInputLayout>(R.id.txtNumeroTicketLayout)
        val txtTitulo = findViewById<EditText>(R.id.txtTituloTicket)
        val txtTituloLayout = findViewById<TextInputLayout>(R.id.txtTituloTicketLayout)
        val txtDescripcion = findViewById<EditText>(R.id.txtDescripcionTicket)
        val txtDescripcionLayout = findViewById<TextInputLayout>(R.id.txtDescripcionTicketLayout)
        val txtDepartamento = findViewById<EditText>(R.id.txtDepartamentoUsuario)
        val txtDepartamentoLayout = findViewById<TextInputLayout>(R.id.txtDepartamentoUsuarioLayout)
        val txtAutor = findViewById<EditText>(R.id.txtAutorTicket)
        val txtAutorLayout = findViewById<TextInputLayout>(R.id.txtAutorTicketLayout)
        val txtEmail = findViewById<EditText>(R.id.txtEmailAutor)
        val txtEmailLayout = findViewById<TextInputLayout>(R.id.txtEmailAutorLayout)
        val txtFechaCreacion = findViewById<EditText>(R.id.txtFechaCreacionTicket)
        val txtFechaCreacionLayout = findViewById<TextInputLayout>(R.id.txtFechaCreacionTicketLayout)
        val txtEstado = findViewById<EditText>(R.id.txtEstadoTicket)
        val txtEstadoLayout = findViewById<TextInputLayout>(R.id.txtEstadoTicketLayout)
        val txtFechaFinalizacion = findViewById<EditText>(R.id.txtFechaFinalizacionTicket)
        val btnGuardarTicket = findViewById<Button>(R.id.btnGuardarTicket)
        val btnListado = findViewById<Button>(R.id.btnVerListadoTickets)
        val imgAtrasTickets = findViewById<ImageView>(R.id.imgAtrasTickets)

        btnGuardarTicket.setOnClickListener {

            if (txtNumero.text.isEmpty() || txtTitulo.text.isEmpty() || txtDescripcion.text.isEmpty() || txtDepartamento.text.isEmpty() || txtAutor.text.isEmpty() || txtEmail.text.isEmpty() || txtFechaCreacion.text.isEmpty() || txtEstado.text.isEmpty()) {
                when {
                    txtNumero.text.isEmpty() -> {
                        txtNumeroLayout.isErrorEnabled = true
                        txtNumeroLayout.error = "El numero es obligatorio"
                        txtTituloLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtTitulo.text.isEmpty() -> {
                        txtTituloLayout.isErrorEnabled = true
                        txtTituloLayout.error = "El titulo es obligatorio"
                        txtNumeroLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtDescripcion.text.isEmpty() -> {
                        txtDescripcionLayout.isErrorEnabled = true
                        txtDescripcionLayout.error = "La descripción es obligatoria"
                        txtNumeroLayout.isErrorEnabled = false
                        txtTituloLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtDepartamento.text.isEmpty() -> {
                        txtDepartamentoLayout.isErrorEnabled = true
                        txtDepartamentoLayout.error = "El departamento es obligatorio"
                        txtNumeroLayout.isErrorEnabled = false
                        txtTituloLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtAutor.text.isEmpty() -> {
                        txtAutorLayout.isErrorEnabled = true
                        txtAutorLayout.error = "El autor es obligatorio"
                        txtNumeroLayout.isErrorEnabled = false
                        txtTituloLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtEmail.text.isEmpty() -> {
                        txtEmailLayout.isErrorEnabled = true
                        txtEmailLayout.error = "El email del autor es obligatorio"
                        txtNumeroLayout.isErrorEnabled = false
                        txtTituloLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtFechaCreacion.text.isEmpty() -> {
                        txtFechaCreacionLayout.isErrorEnabled = true
                        txtFechaCreacionLayout.error = "La fecha de creación es obligatoria"
                        txtNumeroLayout.isErrorEnabled = false
                        txtTituloLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtEstadoLayout.isErrorEnabled = false
                    }

                    txtEstado.text.isEmpty() -> {
                        txtEstadoLayout.isErrorEnabled = true
                        txtEstadoLayout.error = "El estado es obligatorio"
                        txtNumeroLayout.isErrorEnabled = false
                        txtTituloLayout.isErrorEnabled = false
                        txtDescripcionLayout.isErrorEnabled = false
                        txtDepartamentoLayout.isErrorEnabled = false
                        txtAutorLayout.isErrorEnabled = false
                        txtEmailLayout.isErrorEnabled = false
                        txtFechaCreacionLayout.isErrorEnabled = false
                    }
                }
            } else {
                txtNumeroLayout.isErrorEnabled = false
                txtTituloLayout.isErrorEnabled = false
                txtDescripcionLayout.isErrorEnabled = false
                txtDepartamentoLayout.isErrorEnabled = false
                txtAutorLayout.isErrorEnabled = false
                txtEmailLayout.isErrorEnabled = false
                txtFechaCreacionLayout.isErrorEnabled = false
                txtEstadoLayout.isErrorEnabled = false

                val ticketNuevo = DatosDelTicket(
                    "${txtNumero.text}",
                    "${txtTitulo.text}",
                    "${txtDescripcion.text}",
                    "${txtDepartamento.text}",
                    "${txtAutor.text}",
                    "${txtEmail.text}",
                    "${txtFechaCreacion.text}",
                    "${txtEstado.text}",
                    "${txtFechaFinalizacion.text}"
                )
                referencia.push().setValue(ticketNuevo)
                Toast.makeText(this, "Ticket Agregado!", Toast.LENGTH_LONG).show()
                limpiar(
                    txtNumero,
                    txtTitulo,
                    txtDescripcion,
                    txtDepartamento,
                    txtAutor,
                    txtEmail,
                    txtFechaCreacion,
                    txtEstado,
                    txtFechaFinalizacion
                )
            }
        }


        btnListado.setOnClickListener {
            val activityListadoTickets = Intent(this, activity_listado_tickets::class.java)
            startActivity(activityListadoTickets)
        }

        imgAtrasTickets.setOnClickListener {
            val activity_menu = Intent(this, activity_menu::class.java)
            startActivity(activity_menu)
        }

    }

    fun limpiar(vararg textFields: EditText) {
        for (textField in textFields) {
            textField.setText("")
            textField.clearFocus()
        }
    }
}