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

    private lateinit var pet: Pet

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
                    putExtra("name", pet.name)
                    putExtra("birthDate", pet.birthDate)
                    putExtra("type", pet.type)
                    putExtra("color", pet.color)
                    putExtra("size", pet.size)
                }
                startActivity(petData)
                true
            }

            R.id.lastVetVisitMi -> {
                val lastVetVisit = Intent(this, LastPetVetVisitActivity::class.java).apply {
                    putExtra("lastVetVisit", pet.lastVetVisit)
                }
                startActivity(lastVetVisit)
                true
            }

            R.id.lastVaccinationMi -> {
                val lastVaccination = Intent(this, LastVaccinationActivity::class.java).apply {
                    putExtra("lastVaccination", pet.lastVaccination)
                }
                startActivity(lastVaccination)
                true
            }

            else -> false
        }
    }

}