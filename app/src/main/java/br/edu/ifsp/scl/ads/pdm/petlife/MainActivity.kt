package br.edu.ifsp.scl.ads.pdm.petlife

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes {
        const val NAME = "NAME"
        const val BIRTH_DATE = "BIRTH_DATE"
        const val TYPE = "TYPE"
        const val COLOR = "COLOR"
        const val SIZE = "SIZE"
        const val LAST_VET_VISIT = "LAST_VET_VISIT"
        const val LAST_VACCINATION = "LAST_VACCINATION"
    }

    private lateinit var pet: Pet

    private lateinit var pil: ActivityResultLauncher<Intent>
    private lateinit var lvvl: ActivityResultLauncher<Intent>
    private lateinit var lvl: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Pet Dashboard" //this@MainActivity.javaClass.simpleName
        }

        pet = Pet(
            "Anãozinho", "20/05/2011", "Doguinho", "Branco e preto", "pequeno",
            "15/07/2024", "23/07/2024", "10/09/2024"
        )
        updateUI()

        pil = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getStringExtra(NAME)?.let {
                    amb.petNameTv2.text = it
                }

                result.data?.getStringExtra(BIRTH_DATE)?.let {
                    amb.petNameTv2.text = it
                }

                result.data?.getStringExtra(TYPE)?.let {
                    amb.petTypeTv2.text = it
                }

                result.data?.getStringExtra(COLOR)?.let {
                    amb.petColorTv2.text = it
                }

                result.data?.getStringExtra(SIZE)?.let {
                    amb.petSizeTv2.text = it
                }
                updateUI()
            }
        }

        lvvl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
               result.data?.getStringExtra(LAST_VET_VISIT)?.let {
                   amb.lastVetVisitTv2.text = it
               }
               updateUI()
            }
        }

        lvl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                result.data?.getStringExtra(LAST_VACCINATION)?.let {
                    amb.lastVaccinationTv2.text = it
                }
                updateUI()
            }
        }
    }

    private fun updateUI() {
        amb.petNameTv2.text = pet.name
        amb.petBirthDateTv2.text = pet.birthDate
        amb.petTypeTv2.text = pet.type
        amb.petColorTv2.text = pet.color
        amb.petSizeTv2.text = pet.size
        amb.lastVetVisitTv2.text = pet.lastVetVisit
        amb.lastVaccinationTv2.text = pet.lastVaccination
        amb.lastPetshopVisitTv2.text = pet.lastPetshopVist
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.petInfoMi -> {
                val petData = Intent(this, PetInfoActivity::class.java).apply {
                    putExtra(NAME, pet.name)
                    putExtra(BIRTH_DATE, pet.birthDate)
                    putExtra(TYPE, pet.type)
                    putExtra(COLOR, pet.color)
                    putExtra(SIZE, pet.size)
                }
                pil.launch(petData)
                true
            }

            R.id.lastVetVisitMi -> {
                val lastVetVisit = Intent(this, LastPetVetVisitActivity::class.java).apply {
                    putExtra(LAST_VET_VISIT, pet.lastVetVisit)
                }
                lvvl.launch(lastVetVisit)
                true
            }

            R.id.lastVaccinationMi -> {
                val lastVaccination = Intent(this, LastVaccinationActivity::class.java).apply {
                    putExtra(LAST_VACCINATION, pet.lastVaccination)
                }
                lvl.launch(lastVaccination)
                true
            }

            else -> false
        }
    }

}