package HR152213.desafiopracticoii

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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

                        val ticket = "#:$NumeroTicket, $TituloTicket"
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
    }
}