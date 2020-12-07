package com.ubaya.onlineshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_card_layout.view.*

class ItemAdapter(val items:ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(val v: View) : RecyclerView.ViewHolder(v){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.product_card_layout, parent,false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val url = items[position].gambar //GAMBAR
        Picasso.get().load(url).into(holder.v.imgItem) //GAMBAR

        holder.v.txtNamaItem.text = items[position].nama
        holder.v.txtHarga.setText("HARGA: " + items[position].harga.toString())
        holder.v.txtStok.setText("STOK: " + items[position].stok.toString())
        holder.v.txtDeskripsi.text = "DESKRIPSI: " + items[position].deskripsi
        holder.v.txtNamaKategori.text = "Kategori: " + items[position].namaKategori
    }
}