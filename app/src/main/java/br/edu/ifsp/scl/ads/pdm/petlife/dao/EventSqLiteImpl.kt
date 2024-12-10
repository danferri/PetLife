package br.edu.ifsp.scl.ads.pdm.petlife.dao

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetEvent

class EventSqLiteImpl(context: Context): EventDao {
    companion object {
        private const val EVENT_DATABASE_FILE = "event"
        private const val EVENT_TABLE = "event"
        private const val ID_COLUMN = "id"
        private const val PET_EVENT_COLUMN = "pet_event"
        private const val EVENT_DATE_COLUMN = "event_date"
        private const val DESCRIPTION_COLUMN = "description"
        private const val TIME_COLUMN = "time"
        private const val PET_NAME_COLUMN = "pet_name"

        private const val CREATE_EVENT_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $EVENT_TABLE (" +
                "$ID_COLUMN INTEGER PRIMARY KEY, " +
                "$PET_EVENT_COLUMN TEXT NOT NULL, " +
                "$EVENT_DATE_COLUMN TEXT NOT NULL, " +
                "$DESCRIPTION_COLUMN TEXT NOT NULL, " +
                "$TIME_COLUMN TEXT NOT NULL, " +
                "$PET_NAME_COLUMN TEXT NOT NULL, " +
                "FOREIGN KEY ($PET_NAME_COLUMN) REFERENCES ${PetSqLiteImpl.PET_TABLE}(${PetSqLiteImpl.NAME_COLUMN}));"

    }

    private val eventDatabase: SQLiteDatabase = context.openOrCreateDatabase(EVENT_DATABASE_FILE, MODE_PRIVATE, null)

    init {
        try {
            eventDatabase.execSQL(CREATE_EVENT_TABLE_STATEMENT)
        }
        catch (se: SQLiteException) {
            Log.e("PetLife", se.toString())
        }
    }

    override fun createEvent(event: Event) = eventDatabase.insert(EVENT_TABLE, null, eventToContentValues(event))

    override fun retrieveEvent(id: Int): Event {
        val cursor = eventDatabase.query(
            true,
            EVENT_TABLE,
            null,
            "$ID_COLUMN = ?",
            arrayOf(id.toString()),
            null,
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            cursorToEvent(cursor)
        } else {
            Event()
        }
    }

    override fun retrieveEvents(): MutableList<Event> {
        val eventList = mutableListOf<Event>()

        val cursor = eventDatabase.rawQuery("SELECT * FROM $EVENT_TABLE", null)
        while (cursor.moveToNext()) {
            eventList.add(cursorToEvent(cursor))
        }
        return eventList
    }

    override fun retrieveEventsFromPet(petName: String): MutableList<Event> {
        val eventList = mutableListOf<Event>()

        val cursor = eventDatabase.rawQuery("SELECT * FROM $EVENT_TABLE WHERE $PET_NAME_COLUMN = ?", arrayOf(petName))
        while (cursor.moveToNext()) {
            eventList.add(cursorToEvent(cursor))
        }
        return eventList
    }

    override fun updateEvent(event: Event) =  eventDatabase.update(
        EVENT_TABLE,
        eventToContentValues(event),
        "$ID_COLUMN = ?",
        arrayOf(event.id.toString())
    )

    override fun deleteEvent(id: Int) = eventDatabase.delete(
        EVENT_TABLE,
        "$ID_COLUMN = ?",
        arrayOf(id.toString())
    )

    private fun eventToContentValues(event: Event) = ContentValues().apply {
        with(event) {
            put(ID_COLUMN, id)
            put(PET_EVENT_COLUMN, petEvent.toString())
            put(EVENT_DATE_COLUMN, eventDate)
            put(DESCRIPTION_COLUMN, description)
            put(TIME_COLUMN, time)
            put(PET_NAME_COLUMN, petName)
        }
    }

    private fun cursorToEvent(cursor: Cursor) = with(cursor) {
        Event(
            getInt(getColumnIndexOrThrow(ID_COLUMN)),
            getString(getColumnIndexOrThrow(PET_NAME_COLUMN)),
            PetEvent.fromString(getString(getColumnIndexOrThrow(PET_EVENT_COLUMN))),
            getString(getColumnIndexOrThrow(EVENT_DATE_COLUMN)),
            getString(getColumnIndexOrThrow(TIME_COLUMN)),
            getString(getColumnIndexOrThrow(DESCRIPTION_COLUMN))
        )
    }

}