package br.edu.ifsp.scl.ads.pdm.petlife

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.LAST_VET_VISIT
import br.edu.ifsp.scl.ads.pdm.petlife.MainActivity.Constantes.NAME
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastPetVetVisitBinding
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding

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