package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Event (
    var id: Int?,
    var petName: String = "",
    var petEvent: PetEvent,
    var eventDate: String = "",
    var description: String = "",

):Parcelable