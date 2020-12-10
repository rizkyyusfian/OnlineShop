package com.ubaya.onlineshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.order_card_layout.view.*
import kotlinx.android.synthetic.main.product_card_layout.view.*

class OrderAdapter(val orders:ArrayList<Order>): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(val v: View) : RecyclerView.ViewHolder(v){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.order_card_layout, parent,false)
        return OrderAdapter.OrderViewHolder(v)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.v.txtIdOrder.setText("ID ORDER: " + orders[position].idorderhistory.toString())
        holder.v.txtTanggalOrder.text = "TANGGAL ORDER: " + orders[position].tanggalorder
        holder.v.txtTotalItem.setText("TOTAL ITEM: " + orders[position].totalitem.toString())
        holder.v.txtQuantity.setText("QTY: " + orders[position].quantity.toString())
        holder.v.txtSubTotal.setText("GRAND TOTAL: " + orders[position].subtotal.toString())
    }
}