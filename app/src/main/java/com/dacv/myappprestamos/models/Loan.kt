package com.dacv.myappprestamos.models

import com.dacv.myappprestamos.util.Utils.Utils.ABONADO
import com.dacv.myappprestamos.util.Utils.Utils.CANTIDAD_PRESTADA
import com.dacv.myappprestamos.util.Utils.Utils.FECHA
import com.dacv.myappprestamos.util.Utils.Utils.ID
import com.dacv.myappprestamos.util.Utils.Utils.MONTO
import com.dacv.myappprestamos.util.Utils.Utils.NOMBRE
import com.dacv.myappprestamos.util.Utils.Utils.PLAZOS
import com.google.firebase.firestore.FirebaseFirestore
import com.dacv.myappprestamos.util.Utils.Utils.PRESTAMOS
import com.dacv.myappprestamos.util.Utils.Utils.SALDO
import com.dacv.myappprestamos.util.Utils.Utils.TIPO
import com.dacv.myappprestamos.util.Utils.Utils.USUARIOS
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class Loan {

    private val id = 0
    private val name: String? = null
    private val amount = 0
    private val date: String? = null
    private val type : String? = null// Semanal o quincenal
    private val abonos = 0// número de abonos dados
    private val terms = 0 // número de abonos que se tienen que dar
    private val payment = 0 // monto del abono
    private val saldo = 0
    private val abonado = 0 // cantidad acumulada de abonos
    private val expanded = false

    var db = FirebaseFirestore.getInstance()
    var mauth = FirebaseAuth.getInstance()

    fun addLoad(){
        var id = ""
        val cal = GregorianCalendar.getInstance()
        id = cal.get(Calendar.YEAR).toString()+
                cal.get(Calendar.MONTH).toString()+
                cal.get(Calendar.DAY_OF_MONTH).toString()+
                cal.get(Calendar.HOUR).toString()+
                cal.get(Calendar.MINUTE).toString()+
                cal.get(Calendar.SECOND).toString()+
                cal.get(Calendar.MILLISECOND).toString()

        val data = hashMapOf(
            ID to  id,
            NOMBRE to name,
            CANTIDAD_PRESTADA to amount,
            FECHA to date,
            SALDO to saldo,
            TIPO to type,
            MONTO to payment,
            PLAZOS to terms,
            ABONADO to abonado
        )

        val user = mauth.currentUser
        val result = db.collection(USUARIOS)
            .document(user!!.getUid())
            .collection(PRESTAMOS)
            .document(id).set(data)
            .addOnCompleteListener {
                it.isSuccessful
            }

    }

}