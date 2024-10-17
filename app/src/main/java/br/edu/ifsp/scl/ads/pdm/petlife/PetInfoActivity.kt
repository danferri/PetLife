package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.BIRTH_DATE
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.COLOR
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.NAME
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.SIZE
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.TYPE
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityPetInfoBinding

class PetInfoActivity : AppCompatActivity() {
    private val piab: ActivityPetInfoBinding by lazy {
        ActivityPetInfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(piab.root)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        setSupportActionBar(piab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Dados do Pet"
        }

        intent.getStringExtra(NAME)?.also{ parametro ->
            piab.petNameEt.setText(parametro)
        }

        intent.getStringExtra(BIRTH_DATE)?.also{ parametro ->
            piab.petBirthDateEt.setText(parametro)
        }

        intent.getStringExtra(TYPE)?.also{ parametro ->
            piab.petTypeEt.setText(parametro)
        }

        intent.getStringExtra(COLOR)?.also{ parametro ->
            piab.petColorEt.setText(parametro)
        }

        intent.getStringExtra(SIZE)?.also{ parametro ->
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

                piab.petTypeEt.text.toString().let{
                    this.putExtra(TYPE, it)
                }

                piab.petColorEt.text.toString().let{
                    this.putExtra(COLOR, it)
                }

                piab.petSizeEt.text.toString().let{
                    this.putExtra(SIZE, it)
                }
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }
}