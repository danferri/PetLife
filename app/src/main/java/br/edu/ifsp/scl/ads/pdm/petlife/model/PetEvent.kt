package br.edu.ifsp.scl.ads.pdm.petlife.model

enum class PetEvent(val displayName: String) {
    VET_VISIT("Vet visit"),
    PET_SHOP_VISIT("Petshop visit"),
    VACCINATION("Vaccination");

    override fun toString(): String {
        return displayName
    }
}
