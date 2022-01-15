package mwsp.lily.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import mwsp.lily.splashscreen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        binding.usernameTV.text = "Welcome ${mAuth.currentUser!!.email}"
        binding.btnLogout.setOnClickListener{
            mAuth.signOut()
            updateUI(mAuth.currentUser)
        }
        binding.cvKlasemen.setOnClickListener{
            startActivity(Intent(this, KlasemenActivity::class.java))
        }
        binding.cvJadwal.setOnClickListener{
            startActivity(Intent(this, JadwalActivity::class.java))
        }
        binding.cvStatistik.setOnClickListener{
            startActivity(Intent(this, StatistikActivity::class.java))
        }
        binding.cvBerita.setOnClickListener{
            startActivity(Intent(this, BeritaActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser==null){
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}