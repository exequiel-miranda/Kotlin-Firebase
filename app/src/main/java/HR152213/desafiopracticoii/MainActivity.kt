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
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtCorreoElectronico = findViewById<EditText>(R.id.txtCorreoElectronico)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasena)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        val btnIniciarSesion = findViewById<Button>(R.id.btnInicioSesion)

        btnIniciarSesion.setOnClickListener {
            val activity_login = Intent(this, activity_login::class.java)
            startActivity(activity_login)
        }

        btnRegistrarse.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(txtCorreoElectronico.text.toString(), txtContrasena.text.toString()).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}