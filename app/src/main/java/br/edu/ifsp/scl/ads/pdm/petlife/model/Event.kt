package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Event (
    var id: Int? = null,
    var petName: String = "",
    var petEvent: PetEvent = PetEvent.VET_VISIT,
    var eventDate: String = "",
    var description: String = "",

):Parcelable