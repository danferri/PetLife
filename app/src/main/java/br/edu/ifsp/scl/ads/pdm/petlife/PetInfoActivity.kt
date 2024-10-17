package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.BIRTH_DATE
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.NAME
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

        intent.getStringExtra(MainActivity.NAME)?.also{ parametro ->
            piab.petNameEt.setText(parametro)
        }

        intent.getStringExtra(MainActivity.BIRTH_DATE)?.also{ parametro ->
            piab.petBirthDateEt.setText(parametro)
        }

        intent.getStringExtra(MainActivity.TYPE)?.also{ parametro ->
            piab.petTypeEt.setText(parametro)
        }

        intent.getStringExtra(MainActivity.COLOR)?.also{ parametro ->
            piab.petColorEt.setText(parametro)
        }

        intent.getStringExtra(MainActivity.SIZE)?.also{ parametro ->
            piab.petSizeEt.setText(parametro)
        }

        piab.saveBt.setOnClickListener {
            Intent().apply {
                piab.petNameEt.text.toString().let{
                    this.putExtra(NAME, it)
                }

                piab.petBirthDateEt.text.toString().let{
                    this.putExtra(BIRTH_DATE, it)
                }
                setResult(RESULT_OK, this)
            }
            finish()
        }



//        val petName = intent.getStringExtra(MainActivity.NAME)
//        val petBirthDate = intent.getStringExtra("birthDate")
//        val petType = intent.getStringExtra("type")
//        val petColor = intent.getStringExtra("color")
//        val petSize = intent.getStringExtra("size")
//
//        piab.petNameEt.setText(petName)
//        piab.petBirthDateEt.setText(petBirthDate)
//        piab.petTypeEt.setText(petType)
//        piab.petColorEt.setText(petColor)
//        piab.petSizeEt.setText(petSize)

    }

}