package br.edu.ifsp.scl.ads.pdm.petlife.ui


import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityPetBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.PET
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetSize
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetType

class PetActivity : AppCompatActivity() {
    private val apb: ActivityPetBinding by lazy {
        ActivityPetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val petSizeAdapter = ArrayAdapter<PetSize>(this,android.R.layout.simple_spinner_item,
            PetSize.entries.toTypedArray()
        )
        petSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        apb.petSizeSp.adapter = petSizeAdapter

        val petTypeAdapter = ArrayAdapter<PetType>(this,android.R.layout.simple_spinner_item,
            PetType.entries.toTypedArray()
        )
        petTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        apb.petTypeSp.adapter = petTypeAdapter

        val receivedPet = intent.getParcelableExtra<Pet>(PET)
        receivedPet?.let { pet ->
            with(apb) {
                with(pet) {
                    petNameEt.setText(name)
                    petNameEt.isEnabled = false
                    petBirthDateEt.setText(birthDate)

                    val typeIndex = petTypeAdapter.getPosition(type)
                    petTypeSp.setSelection(typeIndex)

                    petColorEt.setText(color)

                    val sizeIndex = petSizeAdapter.getPosition(size)
                    petSizeSp.setSelection(sizeIndex)

                }
            }
        }

        apb.toolbarIn.toolbar.let {
            it.subtitle = if (receivedPet == null) "New Pet" else "Edit Pet"
            setSupportActionBar(it)
        }

        apb.run{

            saveBt.setOnClickListener {
                val newPet = Pet(
                    petNameEt.text.toString(),
                    petBirthDateEt.text.toString(),
                    petTypeAdapter.getItem(petTypeSp.selectedItemId.toInt()) ?: PetType.DOG,
                    petColorEt.text.toString(),
                    petSizeAdapter.getItem(petSizeSp.selectedItemId.toInt()) ?: PetSize.SMALL,
                    receivedPet?.events?: mutableListOf()

                ).let { pet ->
                    Intent().apply {
                        putExtra(Constant.PET, pet)
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
}