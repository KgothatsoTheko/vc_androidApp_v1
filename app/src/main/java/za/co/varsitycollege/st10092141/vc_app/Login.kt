package za.co.varsitycollege.st10092141.vc_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var loginBtn: Button
    private lateinit var createAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        emailText = findViewById(R.id.email2)
        passwordText = findViewById(R.id.password3)
        loginBtn = findViewById(R.id.login_button)
        createAccount = findViewById(R.id.createAccount)

        loginBtn.setOnClickListener{
            login()
        };

        createAccount.setOnClickListener{
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun login(){

        val email = emailText.text.toString().trim()
        val password = passwordText.text.toString().trim()

        // Validate user input
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Perform sign-in using FirebaseAuth
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Login, "Sign-In Successful!", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Login, "Sign-In Failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the sign-in activity
    }
}