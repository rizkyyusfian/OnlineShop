package com.ubaya.onlineshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        holder.v.txtNamaItem.text = items[position].nama
        holder.v.txtHarga.setText(items[position].harga.toString())
        holder.v.txtStok.setText(items[position].stok.toString())
        holder.v.txtDeskripsi.text = items[position].deskripsi
    }
}