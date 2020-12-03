package com.ubaya.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtLogin.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

//        val q = Volley.newRequestQueue(this)
//        val url = "http://ubaya.prototipe.net/nmp160418112/signup.php"
//        var stringRequest = StringRequest(Request.Method.POST, url,
//            {
//                Log.d("apiresult", it)
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK")
//                {
//                    val data = obj.getJSONArray("data")
//                    for(i in 0 until data.length()) {
//                        val playObj = data.getJSONObject(i)
//                        val playlist = Playlist(
//                            playObj.getInt("id"),
//                            playObj.getString("title"),
//                            playObj.getString("subtitle"),
//                            playObj.getString("description"),
//                            playObj.getString("image_url"),
//                            playObj.getInt("num_likes")
//                        )
//                        playlists.add(playlist)
//                    }
//                    updateList()
//                    Log.d("cekisiarray", playlists.toString())
//                }
//            },
//            {
//                Log.e("apiresult", it.message.toString())
//            })
//        q.add(stringRequest)
    }
}