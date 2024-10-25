package za.co.varsitycollege.st10092141.vc_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth;
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var confirmPasswordText: EditText
    private lateinit var registerBtn:Button
    private lateinit var loginText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        emailText = findViewById(R.id.email1)
        passwordText = findViewById(R.id.password1)
        confirmPasswordText = findViewById(R.id.password2)
        registerBtn = findViewById(R.id.register_button)
        loginText = findViewById(R.id.loginTex)

        registerBtn.setOnClickListener{
            register()
        };

        loginText.setOnClickListener{
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun register(){
        val emailStr = emailText.text.toString().trim()
        val passwordStr1 = passwordText.text.toString().trim()
        val passwordStr2 = confirmPasswordText.text.toString().trim()

        // Validate input fields
        if (emailStr.isEmpty() || passwordStr1.isEmpty() || passwordStr2.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (passwordStr1 != passwordStr2) {
            Toast.makeText(this, "Passwords must match!", Toast.LENGTH_SHORT).show()
            return
        }

        // Perform Firebase registration in a coroutine to handle async task
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Create user with Firebase Auth
                auth.createUserWithEmailAndPassword(emailStr, passwordStr1).await()

                // Switch to Main Thread to update UI
                runOnUiThread {
                    Toast.makeText(this@Register, "Registration Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Register, Login::class.java)
                    startActivity(intent)
                    finish() // Close the current activity
                }
            } catch (e: Exception) {
                // Handle the exception and show error message
                runOnUiThread {
                    Toast.makeText(this@Register, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}