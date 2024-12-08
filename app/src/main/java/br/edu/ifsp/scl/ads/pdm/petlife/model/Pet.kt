package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet (
    var name: String = "",
    var birthDate: String = "",
    var type: PetType = PetType.DOG,
    var color: String = "",
    var size: PetSize = PetSize.SMALL,
    var events: MutableList<Event> = mutableListOf()
): Parcelable