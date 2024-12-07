package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet (
    var name: String = "",
    var birthDate: String = "",
    var type: PetType,
    var color: String = "",
    var size: PetSize
): Parcelable