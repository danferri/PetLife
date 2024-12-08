package br.edu.ifsp.scl.ads.pdm.petlife.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.ads.pdm.petlife.R
import java.sql.SQLException

class PetSqLiteImpl(context: Context): PetDao {

    companion object {
        private const val PET_DATABASE_FILE = "pet"
        private const val PET_TABLE = "pet"
        private const val NAME_COLUMN = "name"
        private const val BIRTH_DATE_COLUMN = "birthDate"
        private const val TYPE_COLUMN = "type"
        private const val COLOR_COLUMN = "color"
        private const val SIZE_COLUMN = "size"

        private const val CREATE_PET_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $PET_TABLE (" +
                "$NAME_COLUMN TEXT NOT NULL PRIMARY KEY, " +
                "$BIRTH_DATE_COLUMN TEXT NOT NULL, " +
                "$TYPE_COLUMN TEXT NOT NULL, " +
                "$COLOR_COLUMN TEXT NOT NULL, " +
                "$SIZE_COLUMN TEXT NOT NULL );"

    }

    private val petDatabase: SQLiteDatabase = context.openOrCreateDatabase(PET_DATABASE_FILE, MODE_PRIVATE, null)

    init {
        try {
            petDatabase.execSQL(CREATE_PET_TABLE_STATEMENT)
        }
        catch (se: SQLException) {
            Log.e(context.getString(R.string.app_name), se.toString())
        }
    }

    override fun createPet(pet: Pet) = petDatabase.insert(PET_TABLE, null, petToContentValues(pet))

    override fun retrievePet(name: String): Pet {
        val cursor = petDatabase.query(
            true,
            PET_TABLE,
            null,
            "$NAME_COLUMN = ?",
            arrayOf(name),
            null,
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            cursorToPet(cursor)
        } else {
            Pet()
        }
    }

    override fun retrievePet(): MutableList<Pet> {
        val petList = mutableListOf<Pet>()

        val cursor = petDatabase.rawQuery("SELECT * FROM $PET_TABLE", null)
        while (cursor.moveToNext()) {
            petList.add(cursorToPet(cursor))
        }
        return petList
    }

    override fun updatePet(pet: Pet) =  petDatabase.update(
        PET_TABLE,
        petToContentValues(pet),
        "$NAME_COLUMN = ?",
        arrayOf(pet.name)
    )

    override fun deletePet(name: String) = petDatabase.delete(
        PET_TABLE,
        "$NAME_COLUMN = ?",
        arrayOf(name)
    )

    private fun petToContentValues(pet: Pet) = ContentValues().apply {
        with(pet) {
            put(NAME_COLUMN, name)
            put(BIRTH_DATE_COLUMN, birthDate)
            put(TYPE_COLUMN, type.toString())
            put(COLOR_COLUMN, color)
            put(SIZE_COLUMN, size.toString())
        }
    }

    private fun cursorToPet(cursor: Cursor) = with(cursor) {
        Pet(
            getString(getColumnIndexOrThrow(NAME_COLUMN)),
            getString(getColumnIndexOrThrow(BIRTH_DATE_COLUMN)),
            PetType.fromString(getString(getColumnIndexOrThrow(TYPE_COLUMN))),
            getString(getColumnIndexOrThrow(COLOR_COLUMN)),
            PetSize.fromInt(getInt(getColumnIndexOrThrow(SIZE_COLUMN)))
        )
    }

}