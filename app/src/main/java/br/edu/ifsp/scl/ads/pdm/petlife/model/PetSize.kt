package br.edu.ifsp.scl.ads.pdm.petlife.model


enum class PetSize(val displayName: String) {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    override fun toString(): String {
        return displayName
    }


}