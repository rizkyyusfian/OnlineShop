package com.ubaya.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.product_card_layout.view.*
import org.json.JSONObject

class ItemDetailActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed();
        return true;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "ULALA APP"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra("ITEM_ID", 0)
        val webhost = "http://ubaya.prototipe.net/nmp160418112/"
        var q = Volley.newRequestQueue(this)
        var url = webhost + "getitemdetail.php"
        var stringRequest = object: StringRequest(Request.Method.POST, url, {
                Log.d("apiresult", it)
                var obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")

                    var det = data.getJSONObject(0)
                    Picasso.get().load(det.getString("gambar")).into(imgGambarProduk)
                    txtKat.text = det.getString("namakategori")
                    txtNamaProduk.text = det.getString("nama")
                    txtHargaProduk.text = "Rp " + det.getInt("harga")
                    txtStok.text = det.getInt("stok").toString()
                    txtDeskripsiProduk.text = det.getString("deskripsi")
                }
            }, {
                Log.e("apiresult", it.message.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                var params = HashMap<String, String>()
                params.put("id", id.toString())

                return params
            }
        }
        q.add(stringRequest)

        btnAddCart.setOnClickListener {
            var intent = Intent(this, CartFragment::class.java)
            intent.putExtra("id", id)
            intent.putExtra("qty", 1)
            startActivity(intent)
        }
    }
}