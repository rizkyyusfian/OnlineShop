package com.ubaya.onlineshop

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtSignup.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val q = Volley.newRequestQueue(this)
            val url = "http://ubaya.prototipe.net/nmp160418112/login.php"
            val stringRequest = object: StringRequest(
                Request.Method.POST, url,
                {
                    Log.d("apiresultLogin", it)
                    val obj = JSONObject(it)
                    if (obj.getString("result") == "OK") {

                        //TOAST
                        Toast.makeText(this, "Berhasil Masuk", Toast.LENGTH_LONG).show()
                        //Snackbar.make(login_layout, "Berhasil Masuk", Snackbar.LENGTH_LONG).show()

                        //GET DETAIL USER
                        val data = obj.getJSONArray("data")
                        var det = data.getJSONObject(0)
                        Global.userid = det.getString("id")
                        Global.username = det.getString("username")
                        Global.password = det.getString("password")

                        //INTENT KE MAIN ACTIVITY
                        var intentLogin = Intent(this, MainActivity::class.java)
                        startActivity(intentLogin)

                    } else if (obj.getString("result") == "ERROR_EMAILPASS") {
                        Toast.makeText(this, "Email/Password Salah", Toast.LENGTH_LONG).show()
                        //Snackbar.make(login_layout, "Email/Password Salah", Snackbar.LENGTH_LONG).show()
                    } else if (obj.getString("result") == "ERROR_AKUN") {
                        Toast.makeText(this, "Akun Belum Terdaftar", Toast.LENGTH_LONG).show()
                        //Snackbar.make(login_layout, "Akun Belum Terdaftar", Snackbar.LENGTH_LONG).show()

                    }

                },
                {
                    Log.d("apiresultLogin", it.message.toString())
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    var params = HashMap<String, String>()
                    params.put("email", txtLoginEmail.text.toString())
                    params.put("password", txtLoginPassword.text.toString())
                    return params
                }
            }
            q.add(stringRequest)
        }
    }
}