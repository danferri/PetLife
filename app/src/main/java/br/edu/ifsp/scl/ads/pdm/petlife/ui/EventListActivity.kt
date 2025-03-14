package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.controller.EventController
import br.edu.ifsp.scl.ads.pdm.petlife.controller.MainController
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.ActivityEventListBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.EVENT
import br.edu.ifsp.scl.ads.pdm.petlife.model.Constant.PET
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetSize
import br.edu.ifsp.scl.ads.pdm.petlife.model.PetType


class EventListActivity : AppCompatActivity() {
    private val aelb: ActivityEventListBinding by lazy {
        ActivityEventListBinding.inflate(layoutInflater)
    }

    // Data source
    private var eventList: MutableList<Event> = mutableListOf()

    // Adapter
    private val eventAdapter: EventAdapter by lazy {
        EventAdapter(this, eventList)
    }

    // controller
    private val eventController: EventController by lazy {
        EventController(this)
    }

    private lateinit var earl: ActivityResultLauncher<Intent>

    private val receivedPet: Pet by lazy {
        intent.getParcelableExtra<Pet>(PET)?:Pet()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(aelb.root)

        eventList = receivedPet.events

        earl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val event = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra<Event>(Constant.EVENT)
                } else {
                    result.data?.getParcelableExtra(EVENT, Event::class.java)
                }
                event?.let { receivedEvent ->
                    receivedEvent.petName = receivedPet.name
                    val position = eventList.indexOfFirst { it.id == receivedEvent.id }
                    if (position == -1) {
                        val eventId = eventController.insertEvent(receivedEvent)
                        receivedEvent.id = eventId.toInt()
                        eventList.add(receivedEvent)
                    }
                    else {
                        eventList[position] = receivedEvent
                        eventController.modifyEvent(receivedEvent)
                    }
                    eventAdapter.notifyDataSetChanged()
                }
            }
        }

        aelb.toolbarIn.toolbar.let {
            it.subtitle = "Event List"
            setSupportActionBar(it)
        }

        aelb.eventsLv.adapter = eventAdapter
        registerForContextMenu(aelb.eventsLv)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Pet(
                    receivedPet.name,
                    receivedPet.birthDate,
                    receivedPet.type,
                    receivedPet.color,
                    receivedPet.size,
                    eventList
                ).let { pet ->
                    Intent().apply {
                        putExtra(Constant.PET, pet)
                        setResult(RESULT_OK, this)
                    }
                }
                finish()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.event_add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.addEventMi -> {
            earl.launch(Intent(this, EventActivity::class.java))
            true
        }

        else -> {
            false
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) = menuInflater.inflate(R.menu.context_event_menu, menu)

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterContextMenuInfo).position

        return when(item.itemId) {
            R.id.editEventMi -> {
                Intent(this, EventActivity::class.java).apply {
                    putExtra(EVENT, eventList[position])
                    earl.launch(this)
                }
                true
            }
            R.id.removeEventMi -> {
                eventList[position].id?.let {
                    eventController.removeEvent(it)
                }
                eventList.removeAt(position)
                eventAdapter.notifyDataSetChanged()
                true
            }
            else -> {
                false
            }
        }
    }
}