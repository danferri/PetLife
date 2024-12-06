package br.edu.ifsp.scl.ads.pdm.petlife.ui


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
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
//        val petNameList: MutableList<String> = mutableListOf()
//        petList.forEach{ pet -> petNameList.add(pet.name) }
//        ArrayAdapter(this, android.R.layout.simple_list_item_1, petNameList)

        ArrayAdapter(this, android.R.layout.simple_list_item_1, petList.run{
            val petNameList: MutableList<String> = mutableListOf()
            this.forEach { petNameList.add(it.name) }
            petNameList
        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.toolbarIn.toolbar.let {
            it.subtitle = "Pet List"
            setSupportActionBar(it)
        }

        fillPetList()

        amb.petsLv.adapter = petAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
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