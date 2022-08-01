package com.dacv.myappprestamos.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import java.lang.String
import java.util.*


class Utils {

    object Utils {

        var dia = 0
        var mes = 0
        var year = 0
        var meses = arrayOf(
            "enero",
            "febrero",
            "marzo",
            "abril",
            "mayo",
            "junio",
            "julio",
            "agosto",
            "septiembre",
            "octubre",
            "novienbre",
            "diciembre"
        )
        var mesesCortos = arrayOf(
            "ene",
            "feb",
            "mar",
            "abr",
            "may",
            "jun",
            "jul",
            "ago",
            "sept",
            "oct",
            "nov",
            "dic"
        )
        var c = Calendar.getInstance()


        const val SEMANAL = "Semanal"
        const val QUINCENAL = "Quincenal"
        const val USUARIOS = "usuarios"
        const val PRESTAMOS = "prestamos"
        const val COMPLETADOS = "completados"
        const val BORRADOS = "borrados"
        const val PERSONA = "Persona"
        const val ABONOS = "abonos"
        const val ID = "id"
        const val TIPO = "tipo"
        const val NOMBRE = "nombre"
        const val EMAIL = "email"
        const val FECHA = "fecha"
        const val FECHA_PRESTAMO = "fecha_prestamo"
        const val FECHA_FINAL = "fecha_final"
        const val CANTIDAD_PRESTADA = "cantidadPrestada"
        const val GANANCIA = "ganancia"
        const val MONTO = "monto"
        const val ABONO = "abono"
        const val ABONADO = "abonado"
        const val PLAZOS = "plazos"
        const val SALDO = "saldo"
        const val TOTAL = "total"
        const val RENDIMIENTO = "rendimiento"
        const val INVERTIDO = "invertido"
        const val RECUPERAR = "recuperar"
        const val TOTAL_GANAR = "totalGanar"
        const val TOTAL_RECUPERAR = "totalRecuperar"
        const val TOTAL_COMPLETADO = "totalCompletado"

        fun getDate(textView: TextView) {
            dia = c[Calendar.DAY_OF_MONTH]
            mes = c[Calendar.MONTH]
            year = c[Calendar.YEAR]
            textView.text = String.format(
                Locale.getDefault(), "%d de %s del %d", dia,
                meses[mes], year
            )
        }

        fun getDate(): kotlin.String? {
            dia = c[Calendar.DAY_OF_MONTH]
            mes = c[Calendar.MONTH]
            year = c[Calendar.YEAR]
            return String.format(Locale.getDefault(), "%d de %s del %d", dia, meses[mes], year)
        }


        fun getDate(abreviada: kotlin.String?): kotlin.String? {
            dia = c[Calendar.DAY_OF_MONTH]
            mes = c[Calendar.MONTH]
            year = c[Calendar.YEAR]
            return String.format(Locale.getDefault(), "%d/%s/%d", dia, mesesCortos[mes], year)
        }

        fun getDate(textView: TextView, abreviada: kotlin.String?) {
            dia = c[Calendar.DAY_OF_MONTH]
            mes = c[Calendar.MONTH]
            year = c[Calendar.YEAR]
            textView.text = String.format(
                Locale.getDefault(), "%d/%s/%d", dia,
                mesesCortos[mes], year
            )
        }

        fun setDate(context: Context?, textView: TextView) {
            val datePickerDialog = context?.let {
                DatePickerDialog(
                    it,
                    { datePicker, year, motnhOfYear, dayOfMonth ->
                        textView.setText(
                            String.format(
                                Locale.getDefault(),
                                "%d de %s del %d",
                                dayOfMonth,
                                meses.get(motnhOfYear),
                                year
                            )
                        )
                    }, year, mes, dia
                )
            }
            datePickerDialog?.show()
        }
    }
}