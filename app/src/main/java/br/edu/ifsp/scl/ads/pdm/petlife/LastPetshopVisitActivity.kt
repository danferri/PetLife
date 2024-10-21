package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.LAST_PETSHOP_VISIT
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.LAST_VACCINATION
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastPetshopVisitBinding
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastVaccinationBinding

class LastPetshopVisitActivity : AppCompatActivity() {
    private val lpsvab: ActivityLastPetshopVisitBinding by lazy {
        ActivityLastPetshopVisitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(lpsvab.root)

        window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

        setSupportActionBar(lpsvab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Última ida ao Petshop"
        }

        intent.getStringExtra(LAST_PETSHOP_VISIT)?.also{ parametro ->
            lpsvab.lastPetshopVisitEt.setText(parametro)
        }

        lpsvab.saveBt.setOnClickListener {
            Intent().apply {
                lpsvab.lastPetshopVisitEt.text.toString().let {
                    this.putExtra(LAST_PETSHOP_VISIT, it)
                }
                setResult(RESULT_OK, this)
            }
            finish()
        }
    }
}