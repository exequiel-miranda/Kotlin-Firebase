package HR152213.desafiopracticoii

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class activity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtCorreoElectronico = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaLogin)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val imgAtras = findViewById<ImageView>(R.id.imgAtras)

        imgAtras.setOnClickListener {
            val activity_main = Intent(this, MainActivity::class.java)
            startActivity(activity_main)
        }

        btnIniciarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(txtCorreoElectronico.text.toString(), txtContrasena.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    val activity_tickets = Intent(this, activity_tickets::class.java)
                    startActivity(activity_tickets)
                }else{
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }


    }
}