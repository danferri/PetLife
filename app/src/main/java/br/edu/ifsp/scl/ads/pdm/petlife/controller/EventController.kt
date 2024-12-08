package br.edu.ifsp.scl.ads.pdm.petlife.controller

import br.edu.ifsp.scl.ads.pdm.petlife.dao.EventDao
import br.edu.ifsp.scl.ads.pdm.petlife.dao.EventSqLiteImpl
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.ui.EventListActivity

class EventController (eventListActivity: EventListActivity){
    private val eventDao: EventDao = EventSqLiteImpl(eventListActivity)

    fun insertEvent(event: Event) = eventDao.createEvent(event)
    fun modifyEvent(event: Event) = eventDao.updateEvent(event)
    fun removeEvent(id: Int) = eventDao.deleteEvent(id)
}