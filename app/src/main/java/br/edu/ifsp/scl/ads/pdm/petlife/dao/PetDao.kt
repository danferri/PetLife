package br.edu.ifsp.scl.ads.pdm.petlife.dao

import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet

interface PetDao {
    fun createPet(pet: Pet): Long
    fun retrievePet(name: String): Pet
    fun retrievePet(): MutableList<Pet>
    fun updatePet(pet: Pet): Int
    fun deletePet(name: String): Int
}