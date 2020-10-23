package io.bluetrace.opentrace.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.bluetrace.opentrace.MainActivity
import io.bluetrace.opentrace.R
import kotlinx.android.synthetic.main.fragment_login_email.*


class LoginEmailFragment: AppCompatActivity(), View.OnClickListener {

    var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login_email)

        buttonLogin.setOnClickListener(this)
        link_signup.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val i = view!!.id

        if (i == R.id.buttonLogin) {
            login()
            startActivity(Intent(this, MainActivity::class.java))
        } else if (i == R.id.link_signup) {
            startActivity(Intent(this, RegisterEmailFragment::class.java))
        }
    }

    private fun login () {
        val emailTxt = findViewById<View>(R.id.input_email) as EditText
        var email = emailTxt.text.toString()
        val passwordTxt = findViewById<View>(R.id.input_password) as EditText
        var password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                }
            })

        }else {
            Toast.makeText(this, "Por favor ingresa un correo electrónico y contraseña", Toast.LENGTH_SHORT).show()
        }
    }

}

