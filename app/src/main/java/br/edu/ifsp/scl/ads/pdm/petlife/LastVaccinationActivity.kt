package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.LAST_VACCINATION
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastVaccinationBinding

class LastVaccinationActivity : AppCompatActivity() {
    private val lvab: ActivityLastVaccinationBinding by lazy {
        ActivityLastVaccinationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(lvab.root)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        setSupportActionBar(lvab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Última vacinação"
        }

        intent.getStringExtra(LAST_VACCINATION)?.also{ parametro ->
            lvab.lastVaccinationEt.setText(parametro)
        }

        lvab.saveBt.setOnClickListener {
            Intent().apply {
                lvab.lastVaccinationEt.text.toString().let {
                    this.putExtra(LAST_VACCINATION, it)
                }
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }
}