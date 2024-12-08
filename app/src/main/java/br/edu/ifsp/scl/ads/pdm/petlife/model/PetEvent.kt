package br.edu.ifsp.scl.ads.pdm.petlife.model

enum class PetEvent(val displayName: String) {
    VET_VISIT("Vet visit"),
    PET_SHOP_VISIT("Petshop visit"),
    VACCINATION("Vaccination");

    companion object {
        fun fromString(value: String): PetEvent {
            return PetEvent.values().find { it.displayName.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Invalid PetEvent value: $value")
        }
    }

    override fun toString(): String {
        return displayName
    }
}
