package br.edu.ifsp.scl.ads.pdm.petlife.ui

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.petlife.databinding.TilePetBinding
import br.edu.ifsp.scl.ads.pdm.petlife.model.Pet
import br.edu.ifsp.scl.ads.pdm.petlife.R


class PetAdapter (
    context: Context,
    private val petList: MutableList<Pet>): ArrayAdapter<Pet>(context, R.layout.tile_pet, petList) {

    private data class PetTileHolder(
        val petNameTv: TextView,
        val petTypeTv: TextView
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        lateinit var tpb: TilePetBinding

        val pet = petList[position]

        var petTile = convertView
        if(petTile == null) {

            tpb = TilePetBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            petTile = tpb.root

            val newPetTileHolder = PetTileHolder(tpb.petNameTv, tpb.petTypeTv)

            petTile.tag = newPetTileHolder
        }

        val holder = petTile?.tag as PetTileHolder
        holder.let {
            it.petNameTv.text = pet.name
            it.petTypeTv.text = pet.type.toString()
        }

        return petTile!!
    }

}