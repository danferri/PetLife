package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEventBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.EVENT
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetEvent
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetType

class EventActivity : AppCompatActivity() {
    private val aeb: ActivityEventBinding by lazy {
        ActivityEventBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aeb.root)

        val eventTypeAdapter = ArrayAdapter<PetEvent>(this,android.R.layout.simple_spinner_item,
            PetEvent.entries.toTypedArray()
        )
        eventTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        aeb.eventTypeSp.adapter = eventTypeAdapter

        val receivedEvent = intent.getParcelableExtra<Event>(EVENT)
        receivedEvent?.let { event ->
            with(aeb) {
                with(event) {
                    val eventIndex = eventTypeAdapter.getPosition(petEvent)
                    eventTypeSp.setSelection(eventIndex)

                    eventDateEt.setText(eventDate)
                    eventDescriptionEt.setText(description)
                }
            }
        }

        aeb.toolbarIn.toolbar.let {
            it.subtitle = if (receivedEvent == null) "New event" else "Edit event"
            setSupportActionBar(it)
        }

        aeb.run{
            saveBt.setOnClickListener {
                val newEvent = Event(
                    eventTypeAdapter.getItem(eventTypeSp.selectedItemId.toInt()) ?: PetEvent.VET_VISIT,
                    eventDateEt.text.toString(),
                    eventDescriptionEt.text.toString()
                ).let { event ->
                    Intent().apply {
                        putExtra(Constant.EVENT, event)
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
}