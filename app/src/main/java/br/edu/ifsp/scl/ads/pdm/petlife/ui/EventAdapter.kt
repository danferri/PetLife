package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.petlife.R
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.TileEventBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Event
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet

class EventAdapter (
    context: Context,
    private val eventList: MutableList<Event>): ArrayAdapter<Event>(context, R.layout.tile_event, eventList) {

    private data class EventTileHolder(
        val eventTypeTv: TextView,
        val eventDateTv: TextView
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        lateinit var teb: TileEventBinding

        val event = eventList[position]

        var eventTile = convertView
        if (eventTile == null) {

            teb = TileEventBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            eventTile = teb.root

            val newEventTileHolder = EventTileHolder(teb.EventTypeTv, teb.eventDateTv)

            eventTile.tag = newEventTileHolder
        }

        val holder = eventTile?.tag as EventTileHolder
        holder.let {
            it.eventTypeTv.text = event.petEvent.toString()
            it.eventDateTv.text = event.eventDate
        }

        return eventTile!!
    }
}