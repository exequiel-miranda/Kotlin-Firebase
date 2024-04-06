package HR152213.desafiopracticoii

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class activity_listado_tickets : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_tickets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lsTickets = findViewById<ListView>(R.id.lsTickets)

        val database = FirebaseDatabase.getInstance()
        val referencia = database.getReference("Tickets")

        val datos = mutableListOf<String>()
        val miAdaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)
        lsTickets.adapter = miAdaptador

        var key: String? = null

        fun obtenerDatos(){
            referencia.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    datos.clear()
                    for(dataSnapshot in snapshot.children){
                        key = dataSnapshot.key
                        val NumeroTicket = dataSnapshot.child("numeroTicket").value
                        val TituloTicket = dataSnapshot.child("tituloTicket").value

                        val ticket = "#$NumeroTicket, $TituloTicket"
                        datos.add(ticket)
                    }
                    miAdaptador.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@activity_listado_tickets, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }
        obtenerDatos()

        lsTickets.setOnItemClickListener { adapterView, view, position, id ->

            val dato = datos[position]

            fun eliminarDato(position: Int){
                referencia.child(key!!).removeValue()
            }

            fun actualizarDato(dato: String){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Actualizar ticket")

                val numeroTicket = EditText(this)
                numeroTicket.hint = "Numero del Ticket"
                val tituloTicket = EditText(this)
                tituloTicket.hint = "Titulo del Ticket"
                val DescripcionTicket = EditText(this)
                DescripcionTicket.hint = "Descripción del Ticket"
                val DepartamentoUsuario = EditText(this)
                DepartamentoUsuario.hint = "Departamento del Usuario"
                val AutorTicket = EditText(this)
                AutorTicket.hint = "Autor del ticket"
                val EmailAutor = EditText(this)
                EmailAutor.hint = "Email del autor"
                val FechaCreacion = EditText(this)
                FechaCreacion.hint = "Fecha de creación"
                val EstadoTicket = EditText(this)
                EstadoTicket.hint = "Estado del ticket"
                val FechaFinalizacion = EditText(this)
                FechaFinalizacion.hint = "Fecha de finalización"


                //Agrego los EditText a la alerta
                val layout = LinearLayout(this)
                layout.orientation = LinearLayout.VERTICAL
                layout.addView(numeroTicket)
                layout.addView(tituloTicket)
                layout.addView(DescripcionTicket)
                layout.addView(DepartamentoUsuario)
                layout.addView(AutorTicket)
                layout.addView(EmailAutor)
                layout.addView(FechaCreacion)
                layout.addView(EstadoTicket)
                layout.addView(FechaFinalizacion)

                builder.setView(layout)

                //Agregar botones a la alerta de actualizar datos
                builder.setPositiveButton("Actualizar"){ dialog, which ->
                    //Tomamos los nuevos valores
                    val nuevoNumeroTicket = numeroTicket.text.toString()
                    val nuevoTituloTicket = tituloTicket.text.toString()
                    val nuevaDescripcionTicket = DescripcionTicket.text.toString()
                    val nuevoDepartamentoUsuario = DepartamentoUsuario.text.toString()
                    val nuevoAutorTicket = AutorTicket.text.toString()
                    val nuevoEmailAutor = EmailAutor.text.toString()
                    val nuevaFechaCreacion = FechaCreacion.text.toString()
                    val nuevoEstadoTicket = EstadoTicket.text.toString()
                    val nuevaFechaFinalizacion = FechaFinalizacion.text.toString()




                    //Actualizar dato en la base de datos
                    val DatosDelTicket = DatosDelTicket(nuevoNumeroTicket, nuevoTituloTicket, nuevaDescripcionTicket, nuevoDepartamentoUsuario, nuevoAutorTicket, nuevoEmailAutor,nuevaFechaCreacion, nuevoEstadoTicket, nuevaFechaFinalizacion)
                    referencia.child(key!!).setValue(DatosDelTicket)
                }
                builder.setNegativeButton("Cancelar", null)

                builder.show()
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Elige una opcion")
            builder.setItems(arrayOf("Eliminar", "Actualizar")){ dialog, which ->
                when(which){
                    0 -> eliminarDato(position)
                    1 -> actualizarDato(dato)
                }
            }

            builder.show()
        }

    }
}