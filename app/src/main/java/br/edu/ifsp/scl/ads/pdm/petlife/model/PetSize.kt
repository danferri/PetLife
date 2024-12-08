package br.edu.ifsp.scl.ads.pdm.petlife.model


enum class PetSize(val displayName: String) {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    companion object {
        fun fromString(value: String): PetSize {
            return PetSize.values().find { it.displayName.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid PetSize value: $value")
        }
    }

    override fun toString(): String {
        return displayName
    }

}