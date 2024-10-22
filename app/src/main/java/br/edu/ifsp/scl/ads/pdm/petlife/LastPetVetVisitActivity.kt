package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.LAST_VET_VISIT
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.SITE_VET
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.TEL_VET
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastPetVetVisitBinding


class LastPetVetVisitActivity : AppCompatActivity() {
    private val lpvvab: ActivityLastPetVetVisitBinding by lazy {
        ActivityLastPetVetVisitBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(lpvvab.root)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        setSupportActionBar(lpvvab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Última ida ao vaterinário"
        }

        intent.getStringExtra(LAST_VET_VISIT)?.also{ parametro ->
            lpvvab.lastVetVisitEt.setText(parametro)
        }

        intent.getStringExtra(TEL_VET)?.also{ parametro ->
            lpvvab.telVetEt.setText(parametro)
        }

        intent.getStringExtra(SITE_VET)?.also{ parametro ->
            lpvvab.siteVetTvEt.setText(parametro)
        }

        lpvvab.saveBt.setOnClickListener {
            Intent().apply {
                lpvvab.lastVetVisitEt.text.toString().let {
                    this.putExtra(LAST_VET_VISIT, it)
                }
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }
}