package br.edu.ifsp.scl.ads.pdm.petlife


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
            subtitle = "Pet Dashboard"
        }

        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        pet = Pet(
            "Anãozinho", "20/05/2011", "Doguinho", "Branco e preto", "pequeno",
            "15/07/2024", "23/07/2024", "10/09/2024"
        )
        updateUI()

        pil = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.getStringExtra(NAME)?.let {
                    pet.name = it
                }

                result.data?.getStringExtra(BIRTH_DATE)?.let {
                    pet.birthDate = it
                }

                result.data?.getStringExtra(TYPE)?.let {
                    pet.type = it
                }

                result.data?.getStringExtra(COLOR)?.let {
                    pet.color = it
                }

                result.data?.getStringExtra(SIZE)?.let {
                    pet.size = it
                }
                updateUI()
            }
        }

        lvvl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
               result.data?.getStringExtra(LAST_VET_VISIT)?.let {
                   pet.lastVetVisit = it
               }
               updateUI()
            }
        }

        lvl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                result.data?.getStringExtra(LAST_VACCINATION)?.let {
                    pet.lastVaccination = it
                }
                updateUI()
            }
        }
    }

    private fun updateUI() {
        amb.petNameValueTv.text = pet.name
        amb.petBirthDateValeuTv.text = pet.birthDate
        amb.petTypeValueTv.text = pet.type
        amb.petColorValueTv.text = pet.color
        amb.petSizeValueTv.text = pet.size
        amb.lastVetVisitValueTv.text = pet.lastVetVisit
        amb.lastVaccinationValueTv.text = pet.lastVaccination
        amb.lastPetshopVisitValueTv.text = pet.lastPetshopVist
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