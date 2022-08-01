package com.dacv.myappprestamos.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dacv.myappprestamos.R
import com.dacv.myappprestamos.util.Utils.Utils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.String
import kotlin.Int
import kotlin.toString


class NewLoanFragmentDialog:DialogFragment(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_dialog_new_loan,container,false)

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

        Log.d("DATOS",user.uid)
        Log.d("DATOS",user.email.toString())
        Log.d("DATOS",user.displayName.toString())

        Utils.getDate(rootView.findViewById(R.id.fecha))

        rootView.findViewById<TextView>(R.id.fecha).setOnClickListener(this)
        rootView.findViewById<ImageButton>(R.id.fullscreen_dialog_close).setOnClickListener(this)
        rootView.findViewById<TextView>(R.id.fullscreen_dialog_action).setOnClickListener(this)

        return rootView
    }

    override fun getTheme(): Int {
        return R.style.FullscreenDialogTheme
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.fecha ->{
                Utils.setDate(requireActivity(),v.findViewById(R.id.fecha))
            }
            R.id.fullscreen_dialog_action->{
                Toast.makeText(requireActivity(), "Add", Toast.LENGTH_SHORT).show()
            }
            R.id.fullscreen_dialog_close->{
                dismiss()
            }
        }
    }
}