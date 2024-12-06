package br.edu.ifsp.scl.ads.pdm.petlife.ui


import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet


class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Data source
    private val petList: MutableList<Pet> = mutableListOf()

    // Adapter
    private val petAdapter: ArrayAdapter<String> by lazy {


        ArrayAdapter(this, android.R. simple_list_pet_1, petList)
        val petNameList: MutableList<String> = mutableListOf()
        this.forEach { petNameList.add(it.name) }
        petNameList
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.toolbarIn.toolbar.let {
            it.subtitle = getString(R.string.pet_list)
            setSupportActionBar(it)
        }

        fillPetList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(pet.petName) {
        R.id.addPeMi -> {
            true
        }

        else -> {
            false
        }
    }

    private fun fillPetList() {
        for (index in 1..5) {
            petList.add (
                Pet(
                    "name $index",
                    "birthDate $index",
                    "type $index",
                    "color $index",
                    "size $index"
                )
            )
        }
    }
}