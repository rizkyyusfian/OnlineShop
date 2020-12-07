package com.ubaya.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
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
                    Log.d("apiresult", it)
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        Toast.makeText(this, "Berhasil Masuk", Toast.LENGTH_LONG).show()
                        var intentLogin = Intent(this, MainActivity::class.java)
                        startActivity(intentLogin)
                    } else if (obj.getString("result") == "ERROR_EMAILPASS") {
                        Toast.makeText(this, "Email/Password Salah", Toast.LENGTH_LONG).show()
                    } else if (obj.getString("result") == "ERROR_AKUN") {
                        Toast.makeText(this, "Akun Belum Terdaftar", Toast.LENGTH_LONG).show()
//                        Snackbar.make(this, "Join Succed", Snackbar.LENGTH_LONG).show()
                    }

                },
                {
                    Log.d("apiresult", it.message.toString())
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