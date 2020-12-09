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
import org.json.JSONObject

class ItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val id = intent.getIntExtra("ITEM_ID", 0)
        val webhost = "http://ubaya.prototipe.net/s160418112/"
        var q = Volley.newRequestQueue(this)
        var url = webhost + "getitemdetail.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url, Response.Listener {
                Log.d("apiresult", it)
                var obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")

                    var det = data.getJSONObject(0)
                    Picasso.get().load(webhost + "gambar/" + det.getString("gambar")).into(imgGambarProduk)
                    txtKat.text = det.getString("namakategori")
                    txtNamaProduk.text = det.getString("nama")
                    txtHargaProduk.text = "Rp " + det.getInt("harga")
                    txtStok.text = det.getInt("stok").toString()
                    txtDeskripsiProduk.text = det.getString("deskripsi")
                }
            }, Response.ErrorListener {
                Log.e("apiresult", it.message.toString())
            }
        )
        // TODO: Ditambahi yang untuk ngePOST parameter "id"
        // {
//            override fun getParams(): MutableMap<String, Int> {
//                var params = HashMap<String, Int>()
//                var id = intent.getIntExtra("ITEM_ID", 0)
//                params.put("id", id)
//                return params
//            }
//        }
        q.add(stringRequest)

        btnAddCart.setOnClickListener {
            var intent = Intent(this, CartFragment::class.java)
            intent.putExtra("id", id)
            intent.putExtra("qty", 1)
            startActivity(intent)
        }
    }
}