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
        setContentView(R.layout.activity_login)

        val txtCorreoElectronico = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtContrasena = findViewById<EditText>(R.id.txtContrasenaLogin)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val imgAtras = findViewById<ImageView>(R.id.imgAtras)

        imgAtras.setOnClickListener {
            val activity_main = Intent(this, MainActivity::class.java)
            startActivity(activity_main)
        }

        btnIniciarSesion.setOnClickListener {

            if(txtCorreoElectronico.text.isEmpty() || txtContrasena.text.isEmpty()){
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show()
            }else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    txtCorreoElectronico.text.toString(),
                    txtContrasena.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val activity_menu = Intent(this, activity_menu::class.java)
                        startActivity(activity_menu)
                    } else {
                        Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }


    }
}