package com.ubaya.onlineshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        btnSignup.setOnClickListener {
            if (txtSignupPassword.text.toString() == txtSignupCekPassword.text.toString()) {
                val q = Volley.newRequestQueue(this)
                val url = "http://ubaya.prototipe.net/nmp160418112/signup.php"
                val stringRequest = object: StringRequest(Request.Method.POST, url,
                    {
                        Log.d("apiresult", it)
                        Toast.makeText(this, "Akun Berhasil Didaftarkan", Toast.LENGTH_LONG).show()
                    },
                    {
                        Log.d("apiresult", it.message.toString())
                    }
                ) {
                    override fun getParams(): MutableMap<String, String> {
                        var params = HashMap<String, String>()
                        params.put("username", txtSignupUsername.text.toString())
                        params.put("email", txtSignupEmail.text.toString())
                        params.put("password", txtSignupPassword.text.toString())
                        return params
                    }
                }
                q.add(stringRequest)
            } else {
                Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_LONG).show()
            }
        }
    }
}