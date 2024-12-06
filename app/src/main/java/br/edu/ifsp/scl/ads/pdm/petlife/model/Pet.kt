package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Pet (
    var name: String = "",
    var birthDate: String = "",
    var type: String = "",
    var color: String = "",
    var size: String = ""
): Parcelable