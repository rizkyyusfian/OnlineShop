package com.ubaya.onlineshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_card_layout.view.*

class ItemAdapter(val items:ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(val v: View) : RecyclerView.ViewHolder(v){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.product_card_layout, parent,false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val url = items[position].gambar //GAMBAR
        Picasso.get().load(url).into(holder.v.imgItem) //GAMBAR

        with(holder.v) {
            txtNamaItem.text = items[position].nama
            txtHarga.setText("HARGA: " + items[position].harga.toString())
            txtDeskripsi.text = items[position].deskripsi

            cardViewDetail.setOnClickListener {
                var intent = Intent(context, ItemDetailActivity::class.java)
                intent.putExtra("ITEM_ID", items[position].id)
                context.startActivity(intent)
            }
        }
    }
}