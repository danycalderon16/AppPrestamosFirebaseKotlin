package com.dacv.myappprestamos.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.dacv.myappprestamos.R
import com.dacv.myappprestamos.databinding.ActivityMainBinding
import com.dacv.myappprestamos.fragments.NewLoanFragmentDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)

        binding.fab.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_complete ->{
                Toast.makeText(this, "Proximamente...", Toast.LENGTH_SHORT).show()
            }
            R.id.action_logout ->{
                logout()
            }
            R.id.action_deletes_items ->{
                Toast.makeText(this, "Proximamente...", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        googleSignInClient.signOut().addOnCompleteListener {
            Firebase.auth.signOut()
            googleSignInClient.revokeAccess()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id){
                R.id.fab -> showFullDialog()
            }
        }
    }

    private fun showFullDialog() {
        var dialog = NewLoanFragmentDialog()
        dialog.show(supportFragmentManager, "loanFragmentDialog")
    }
}