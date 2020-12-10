package com.ubaya.onlineshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_card_layout.view.*

class CartAdapter(val carts: ArrayList<Cart>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(val v: View): RecyclerView.ViewHolder(v) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cart_card_layout, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val url = carts[position].gambar
        var grandTotal = Global.carts[position].jumlah_item * Global.carts[position].harga_item
        with(holder.v) {
            Picasso.get().load(url).into(imgProductCart)
            txtNameCart.text = carts[position].nama_item
            txtQtyCart.text = carts[position].jumlah_item.toString()
            txtTotalPriceCart.text = grandTotal.toString()

            txtAdd.setOnClickListener {
                Global.carts[position].jumlah_item ++
            }

            txtSubt.setOnClickListener {
                if (Global.carts[position].jumlah_item > 0) {
                    Global.carts[position].jumlah_item --
                }
            }
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}