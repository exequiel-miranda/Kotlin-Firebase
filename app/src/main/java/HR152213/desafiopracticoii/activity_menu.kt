package HR152213.desafiopracticoii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class activity_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnAgregarTicket = findViewById<Button>(R.id.btnAgregarTicket)
        val btnVerListado = findViewById<Button>(R.id.btnVerListado)
        val txtCerrarSesion = findViewById<TextView>(R.id.txtCerrarSesion)

        btnAgregarTicket.setOnClickListener {
            val activity_tickets = Intent(this, activity_tickets::class.java)
            startActivity(activity_tickets)
        }

        btnVerListado.setOnClickListener {
            val activity_listado_tickets = Intent(this, activity_listado_tickets::class.java)
            startActivity(activity_listado_tickets)
        }

        txtCerrarSesion.setOnClickListener {
            val activity_main = Intent(this, MainActivity::class.java)
            startActivity(activity_main)
        }
    }
}