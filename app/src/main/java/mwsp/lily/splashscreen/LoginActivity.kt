package mwsp.lily.splashscreen

// 5200411120 Roselilie Simbulan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mwsp.lily.splashscreen.R.layout
import mwsp.lily.splashscreen.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener{
            var email = binding.emailTv.text.toString()
            var password = binding.passwordTv.text.toString()

            if (email.isEmpty())
            {
                binding.emailTv.error = "Email required!"
                binding.emailTv.requestFocus()
            }
            else if (password.isEmpty())
            {
                binding.passwordTv.error = "Password required!"
                binding.passwordTv.requestFocus()
            }
            else
            {
                //login
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful)
                    {
                        Toast.makeText(this,"User Login Successful", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"User Login Failed due to ${it.exception}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.btnRegister.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }




//    fun login(view: android.view.View) {
//
//        val username= binding.InputUsername.text.toString()
//        val pass= binding.InputPassword.text.toString()
//        if(username.isEmpty() || pass.isEmpty()){
//            Toast.makeText(this, "Username / Password is empty", Toast.LENGTH_SHORT).show()
//        }else {
//            FirebaseAuth.getInstance().signInWithEmailAndPassword(username,pass)
//                .addOnCompleteListener{
//                    if(!it.isSuccessful){
//                        return@addOnCompleteListener
//                        Toast.makeText(this, "Username / Password Salah", Toast.LENGTH_LONG).show()
//                    } else {
//                        startActivity(Intent(this, MainActivity::class.java))
//                        finish()
//                    }
//                }.addOnFailureListener{
//                    Toast.makeText(this, "Salah", Toast.LENGTH_LONG).show()
//                }
//        }
//
//    }
}