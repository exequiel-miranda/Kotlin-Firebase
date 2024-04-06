package HR152213.desafiopracticoii

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
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
        setContentView(R.layout.activity_listado_tickets)


        val lsTickets = findViewById<ListView>(R.id.lsTickets)
        val imgAtrasListado = findViewById<ImageView>(R.id.imgAtrasListado)

        imgAtrasListado.setOnClickListener {
            val activity_menu = Intent(this, activity_menu::class.java)
            startActivity(activity_menu)
        }

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
                        val DescripcionTicket = dataSnapshot.child("descripcionTicket").value
                        val DepartamentoUsuario = dataSnapshot.child("departamentoUsuario").value
                        val autorTicket = dataSnapshot.child("autorTicket").value
                        val emailTicket = dataSnapshot.child("emailTicket").value
                        val fechaCreacionTicket = dataSnapshot.child("fechaCreacionTicket").value
                        val estadoTicket = dataSnapshot.child("estadoTicket").value
                        val fechaFinalizacionTicket = dataSnapshot.child("fechaFinalizacionTicket").value

                        val ticket = "#$NumeroTicket, $TituloTicket, $DescripcionTicket, $DepartamentoUsuario, $autorTicket, $emailTicket, $fechaCreacionTicket, $estadoTicket, $fechaFinalizacionTicket"
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

            val datoSeleccionado = datos[position]
            val posicionDatoSeleccionado = position

            fun eliminarDato(position: Int){
                val ticketSeleccionado = datos[position]
                val datosTicket = ticketSeleccionado.split(", ")

                val numeroTicket = datosTicket[0].substring(1)
                val tituloTicket = datosTicket[1]

                referencia.orderByChild("numeroTicket").equalTo(numeroTicket)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (dataSnapshot in snapshot.children) {
                                if (dataSnapshot.child("tituloTicket").value == tituloTicket) {
                                    dataSnapshot.ref.removeValue()
                                        .addOnSuccessListener {
                                            Toast.makeText(this@activity_listado_tickets, "Ticket eliminado correctamente", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(this@activity_listado_tickets, "Error al eliminar ticket: ${e.message}", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(this@activity_listado_tickets, "Error al eliminar ticket", Toast.LENGTH_SHORT).show()
                        }
                    })
            }

            fun actualizarDato(dato: String){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Actualizar ticket")

                val numeroTicket = EditText(this)
                numeroTicket.hint = "Numero del Ticket"
                val datosTicket = dato.split(", ")
                numeroTicket.setText(datosTicket[0].substring(1))


                val tituloTicket = EditText(this)
                tituloTicket.setText(datosTicket[1])

                val DescripcionTicket = EditText(this)
                DescripcionTicket.setText(datosTicket[2])

                val DepartamentoUsuario = EditText(this)
                DepartamentoUsuario.setText(datosTicket[3])


                val AutorTicket = EditText(this)
                AutorTicket.setText(datosTicket[4])

                val EmailAutor = EditText(this)
                EmailAutor.setText(datosTicket[5])

                val FechaCreacion = EditText(this)
                FechaCreacion.setText(datosTicket[6])

                val EstadoTicket = EditText(this)
                EstadoTicket.setText(datosTicket[7])

                val FechaFinalizacion = EditText(this)
                FechaFinalizacion.setText(datosTicket[8])


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
                    val objDatosDelTicket = DatosDelTicket(nuevoNumeroTicket, nuevoTituloTicket, nuevaDescripcionTicket, nuevoDepartamentoUsuario, nuevoAutorTicket, nuevoEmailAutor,nuevaFechaCreacion, nuevoEstadoTicket, nuevaFechaFinalizacion)
                    referencia.child(key!!).setValue(objDatosDelTicket)

                }
                builder.setNegativeButton("Cancelar", null)

                builder.show()
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Elige una opcion")
            builder.setItems(arrayOf("Eliminar", "Actualizar")){ dialog, which ->


                when(which){
                    0 -> eliminarDato(position)
                    1 -> actualizarDato(datoSeleccionado)
                }
            }
            builder.show()
        }
    }
}