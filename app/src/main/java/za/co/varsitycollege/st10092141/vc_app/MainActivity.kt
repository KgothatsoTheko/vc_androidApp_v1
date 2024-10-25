package za.co.varsitycollege.st10092141.vc_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import za.co.varsitycollege.st10092141.vc_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        //method to change navigation depending on which item is selected (Add on from Dima Ps method⬇️)
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.qrScanner -> replaceFragment((QrScanner()))
                else -> {}
            }
            true
        }

    }

    public fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}