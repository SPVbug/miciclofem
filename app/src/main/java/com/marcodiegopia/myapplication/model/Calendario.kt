package com.marcodiegopia.myapplication.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Calendario(
    var fid: String? = "",
    var fechaInicial: String? = "",
    var fechaFinal: String? = "",
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "fid" to fid,
            "fechaInicial" to fechaInicial,
            "fechaFinal" to fechaFinal,
        )
    }
}
