package br.edu.ifsp.scl.ads.pdm.petlife

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityLastVaccinationBinding

class LastVaccinationActivity : AppCompatActivity() {
    private val lvab: ActivityLastVaccinationBinding by lazy {
        ActivityLastVaccinationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(lvab.root)

        setSupportActionBar(lvab.toolbarTb)
        supportActionBar?.apply {
            title = getString(R.string.app_name)
            subtitle = "Última vacinação" //this@MainActivity.javaClass.simpleName
        }

        val lastVaccination = intent.getStringExtra("lastVaccination")

        lvab.lastVaccinationEt.setText(lastVaccination)


    }
}