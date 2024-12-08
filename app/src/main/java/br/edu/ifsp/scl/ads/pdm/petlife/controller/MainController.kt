package br.edu.ifsp.scl.ads.pdm.petlife.controller

import br.edu.ifsp.scl.ads.pdm.petlife.dao.EventDao
import br.edu.ifsp.scl.ads.pdm.petlife.dao.EventSqLiteImpl
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.dao.PetDao
import br.edu.ifsp.scl.ads.pdm.petlife.dao.PetSqLiteImpl
import br.edu.ifsp.scl.ads.pdm.petlife.ui.MainActivity

class MainController(mainActivity: MainActivity) {
    private val petDao: PetDao = PetSqLiteImpl(mainActivity)
    private val eventDao: EventDao = EventSqLiteImpl(mainActivity)

    fun insertPet(pet: Pet) = petDao.createPet(pet)
    fun getPet(name: String) = petDao.retrievePet(name)

    fun getPets(): MutableList<Pet> {
        val petList = petDao.retrievePet()

        petList.forEach { pet ->
            val eventList = eventDao.retrieveEventsFromPet(pet.name)
            pet.events.addAll(eventList)
        }

        return petList
    }

    fun modifyPet(pet: Pet) = petDao.updatePet(pet)
    fun removePet(name: String) = petDao.deletePet(name)


}