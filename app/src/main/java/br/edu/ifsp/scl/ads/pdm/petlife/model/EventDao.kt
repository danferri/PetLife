package br.edu.ifsp.scl.ads.pdm.petlife.model

interface EventDao {
    fun createEvent(event: Event, petName: String): Long
    fun retrieveEvents(petName: String): MutableList<Event>
    fun updateEvent(event: Event, petName: String): Int
    fun deleteEvent(petEvent: String, petName: String): Int
}