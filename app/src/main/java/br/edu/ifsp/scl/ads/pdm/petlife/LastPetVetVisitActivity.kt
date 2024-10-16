package br.edu.ifsp.scl.ads.pdm.petlife

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastPetVetVisitBinding
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityMainBinding

class LastPetVetVisitActivity : AppCompatActivity() {
    private val lpvvab: ActivityLastPetVetVisitBinding by lazy {
        ActivityLastPetVetVisitBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(lpvvab.root)

        setSupportActionBar(lpvvab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Última ida ao vaterinário" //this@MainActivity.javaClass.simpleName
        }


    }
}