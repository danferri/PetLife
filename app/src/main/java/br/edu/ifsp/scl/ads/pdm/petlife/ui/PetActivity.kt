package br.edu.ifsp.scl.ads.pdm.petlife.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityPetBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.PET
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet

class PetActivity : AppCompatActivity() {
    private val apb: ActivityPetBinding by lazy {
        ActivityPetBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val receivedPet = intent.getParcelableExtra<Pet>(PET)
        receivedPet?.let { pet ->
            with(apb) {
                with(pet) {
                    petNameEt.setText(name)
                    petBirthDateEt.setText(birthDate)
                    //petTypeSp.setText = (type)
                    petColorEt.setText(color)
                    //petSizeSp.setText = (size)

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
                    //petTypeSp.text.toString(),
                    petColorEt.text.toString()
                    //petSizeSp.text.toString().toInt()

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