package br.edu.ifsp.scl.ads.pdm.petlife.ui


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.controller.MainController
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.PET
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.VIEW_MODE
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetSize
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetType


class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Data source
    private val petList: MutableList<Pet> = mutableListOf()

    // Adapter
    private val petAdapter: PetAdapter by lazy {
        PetAdapter(this, petList)
    }

    // Controller
    private val mainController: MainController by lazy {
        MainController(this)
    }

    private lateinit var parl: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        parl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val pet = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra<Pet>(Constant.PET)
                } else {
                    result.data?.getParcelableExtra(Constant.PET, Pet::class.java)
                }
                pet?.let{ receivedPet ->
                    val position = petList.indexOfFirst { it.name == receivedPet.name  }
                    if (position == -1) {
                        petList.add(receivedPet)
                        mainController.insertPet(receivedPet)
                    }
                    else {
                        petList[position] = receivedPet
                        mainController.modifyPet(receivedPet)
                    }
                    petAdapter.notifyDataSetChanged()
                }
            }
        }

        amb.toolbarIn.toolbar.let {
            it.subtitle = "Pet List"
            setSupportActionBar(it)
        }

        fillPetList()

        amb.petsLv.adapter = petAdapter

        amb.petsLv.setOnItemClickListener { _, _, position, _ ->
            Intent(this, EventListActivity::class.java).apply {
                putExtra(PET, petList[position])
                putExtra(VIEW_MODE, true)
                parl.launch(this)
            }
        }

        registerForContextMenu(amb.petsLv)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.addPeMi -> {
            parl.launch(Intent(this, PetActivity::class.java))
            true
        }

        else -> {
            false
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) = menuInflater.inflate(R.menu.context_main_menu, menu)

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterContextMenuInfo).position

        return when(item.itemId) {
            R.id.editPetMi -> {
                Intent(this, PetActivity::class.java).apply {
                    putExtra(PET, petList[position])
                    parl.launch(this)
                }
                true
            }
            R.id.removePetMi -> {
                mainController.removePet(petList[position])
                petList.removeAt(position)
                petAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun fillPetList() {
        Thread {
            runOnUiThread {
                petList.clear()
                petList.addAll(mainController.getPets())
                petAdapter.notifyDataSetChanged()
            }
        }.start()
    }
}