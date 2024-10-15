package br.edu.ifsp.scl.ads.pdm.petlife

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityPetInfoBinding

class PetInfoActivity : AppCompatActivity() {
    private val apib: ActivityPetInfoBinding by lazy {
        ActivityPetInfoBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apib.root)

        setSupportActionBar(apib.toolbarTb)
        supportActionBar?.apply {
            title = "PetLife"
            subtitle = "Dados do Pet"
        }

        apib.petNameTv.text = "Nome do Pet: Max"
        //apib.petBreedTv.text = "Raça do Pet: Labrador"


    }

}