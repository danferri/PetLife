package br.edu.ifsp.scl.ads.pdm.petlife.model


enum class PetSize(val sizeValue: Int, val displayName: String) {
    SMALL(1,"Small"),
    MEDIUM(2,"Medium"),
    LARGE(3,"Large"),
    UNKNOWN(0, "Unknown");

    companion object {
        fun fromInt(value: Int): PetSize {
            return values().find { it.sizeValue == value }
                ?: throw IllegalArgumentException("Invalid PetSize value: $value")
        }
    }

    override fun toString(): String {
        return displayName
    }

}