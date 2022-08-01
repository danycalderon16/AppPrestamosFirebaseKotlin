package com.dacv.myappprestamos.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.dacv.myappprestamos.R
import com.dacv.myappprestamos.models.Loan
import com.dacv.myappprestamos.util.Utils.Utils
import com.google.android.material.textfield.TextInputLayout
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

    private lateinit var tilName: TextInputLayout
    private lateinit var tvDate: TextView
    private lateinit var tilAmount: TextInputLayout
    private lateinit var tilTerms: TextInputLayout
    private lateinit var tilPayment: TextInputLayout
    private lateinit var rgTypePayment: RadioGroup
    private lateinit var rbWeekly: RadioButton
    private lateinit var rbBeweekly: RadioButton


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

        tilAmount = rootView.findViewById(R.id.cantidad)
        tilName = rootView.findViewById(R.id.nombre)
        tilPayment = rootView.findViewById(R.id.pagos)
        tilTerms = rootView.findViewById(R.id.plazos)
        tvDate = rootView.findViewById(R.id.fecha)

        rgTypePayment = rootView.findViewById(R.id.radio_group)
        rbBeweekly = rootView.findViewById(R.id.quincenal)
        rbWeekly = rootView.findViewById(R.id.semanal)

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
                var data = tilName.editText?.text.toString()+"\n"+
                        tvDate.text.toString()+"\n"+
                        tilAmount.editText?.text.toString()+"\n"+
                        tilPayment.editText?.text.toString()+"\n"+
                        tilTerms.editText?.text.toString()+"\n"

                Log.d("Datos!!!!",data)
                val loan = Loan()
                val result = loan.addLoad()
                Log.d("RESULT: ",result.toString())
            }
            R.id.fullscreen_dialog_close->{
                dismiss()
            }
        }
    }
}