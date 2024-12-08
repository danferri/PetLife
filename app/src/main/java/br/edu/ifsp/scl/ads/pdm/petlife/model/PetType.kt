package br.edu.ifsp.scl.ads.pdm.petlife.model

enum class PetType(val displayName: String) {
    DOG("Dog"),
    CAT("Cat");

    companion object {
        fun fromString(value: String): PetType {
            return values().find { it.displayName.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid PetType value: $value")
        }
    }

    override fun toString(): String {
        return displayName
    }
}