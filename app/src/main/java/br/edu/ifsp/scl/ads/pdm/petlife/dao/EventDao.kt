package br.edu.ifsp.scl.ads.pdm.petlife.dao

import br.edu.ifsp.scl.ads.pdm.petlife.model.Event

interface EventDao {
    fun createEvent(event: Event): Long
    fun retrieveEvents(): MutableList<Event>
    fun retrieveEvent(id: Int): Event
    fun retrieveEventsFromPet(petName: String): MutableList<Event>
    fun updateEvent(event: Event): Int
    fun deleteEvent(id: Int): Int
}