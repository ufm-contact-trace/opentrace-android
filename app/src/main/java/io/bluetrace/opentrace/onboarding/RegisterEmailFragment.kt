package io.bluetrace.opentrace.onboarding


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import io.bluetrace.opentrace.R
import kotlinx.android.synthetic.main.fragment_register_email.*


class RegisterEmailFragment: AppCompatActivity(), View.OnClickListener {

    var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register_email)

        buttonRegister.setOnClickListener(this)
        link_login.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        val i = view!!.id

        if (i == R.id.buttonRegister) {
            registerUser()
            startActivity(Intent(this, OnboardingActivity::class.java))
        } else if (i == R.id.link_login) {
            startActivity(Intent(this, LoginEmailFragment::class.java))
        }
    }

    private fun registerUser () {
        val emailTxt = findViewById<View>(R.id.input_email) as EditText
        val passwordTxt = findViewById<View>(R.id.input_password) as EditText
        val againpasswordTxt = findViewById<View>(R.id.input_reEnterPassword) as EditText


        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var againpassword = againpasswordTxt.text.toString()


        fun String.isEmailValid() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

        if (!email.isEmpty() && !password.isEmpty() && !againpassword.isEmpty() && email.isEmailValid()) {
            if (password == againpassword){
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        Toast.makeText(this, "Registrado excitosamente", Toast.LENGTH_LONG).show()
                    }else {
                        Toast.makeText(this, "Error al registrarse", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
        }else {
            Toast.makeText(this, "Ingrese un correo y contraseña", Toast.LENGTH_LONG).show()
        }
    }
}

