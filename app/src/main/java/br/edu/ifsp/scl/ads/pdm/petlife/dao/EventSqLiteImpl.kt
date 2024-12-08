package br.edu.ifsp.scl.ads.pdm.petlife.dao

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event

class EventSqLiteImpl(context: Context): EventDao {
    companion object {
        private const val EVENT_DATABASE_FILE = "event"
        private const val EVENT_TABLE = "event"
        private const val ID_COLUMN = "id"
        private const val PET_EVENT_COLUMN = "pet_event"
        private const val EVENT_DATE_COLUMN = "event_date"
        private const val DESCRIPTION_COLUMN = "description"
        private const val PET_NAME_COLUMN = "pet_name"

        private const val CREATE_EVENT_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $EVENT_TABLE (" +
                "$ID_COLUMN INTEGER PRIMARY KEY, " +
                "$PET_EVENT_COLUMN TEXT NOT NULL, " +
                "$EVENT_DATE_COLUMN TEXT NOT NULL, " +
                "$DESCRIPTION_COLUMN TEXT NOT NULL, " +
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






    private fun eventToContentValues(event: Event) = ContentValues().apply {
        with(event) {
            put(ID_COLUMN, id)
            put(PET_EVENT_COLUMN, petEvent.toString())
            put(EVENT_DATE_COLUMN, eventDate)
            put(DESCRIPTION_COLUMN, description)
            put(PET_NAME_COLUMN, petName)
        }
    }

}