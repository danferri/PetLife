package br.edu.ifsp.scl.ads.pdm.petlife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityPetInfoBinding

class PetInfoActivity : AppCompatActivity() {
    private val piab: ActivityPetInfoBinding by lazy {
        ActivityPetInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(piab.root)

        setSupportActionBar(piab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Dados do Pet" //this@MainActivity.javaClass.simpleName
        }

        val petName = intent.getStringExtra("name")
        val petBirthDate = intent.getStringExtra("birthDate")
        val petType = intent.getStringExtra("type")
        val petColor = intent.getStringExtra("color")
        val petSize = intent.getStringExtra("size")

        piab.petNameEt.setText(petName)
        piab.petBirthDateEt.setText(petBirthDate)
        piab.petTypeEt.setText(petType)
        piab.petColorEt.setText(petColor)
        piab.petSizeEt.setText(petSize)

    }

}