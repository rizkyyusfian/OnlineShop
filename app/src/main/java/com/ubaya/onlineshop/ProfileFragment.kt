package com.ubaya.onlineshop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var v:View ?= null
    var id:Int ?= 0
    var username:String ?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onStart() {
        super.onStart()
//        txtProfileNama.setText(username)


        val getArgumentId = arguments!!.getString("ITEM_USERID")
        val getArgumentUsername = arguments!!.getString("ITEM_USERNAME")

        txtProfileNama.setText(getArgumentUsername)
        txtProfilePassLama.setText((getArgumentId))

        Log.d("tes1onStart", getArgumentId.toString())
        Log.d("tes2onStart", getArgumentUsername.toString())
    }

    override fun onResume() {
        super.onResume()

        btnEditNama.setOnClickListener {
            Log.d("tes1", id.toString())
            Log.d("tes2", username.toString())

            val q = Volley.newRequestQueue(context)
            val url = "http://ubaya.prototipe.net/nmp160418112/changeusername.php"
            val stringRequest = object: StringRequest(Request.Method.POST, url,
                {
                    Log.d("apiresultEDIT", it)
                    val obj = JSONObject(it)
                    if (obj.getString("result") == "OK") {
                        Toast.makeText(context, "Username Berhasil Diubah", Toast.LENGTH_LONG)
                            .show()
                    } else if (obj.getString("result") == "ERROR_UPDATE") {
                        Toast.makeText(context, "Gagal Ubah Username, Coba Lagi", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                {
                    Log.d("apiresultEDIT", it.message.toString())
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    var params = HashMap<String, String>()
                    params.put("id", txtProfilePass.toString())
                    params.put("username", txtProfileNama.toString())
                    return params
                }
            }
            q.add(stringRequest)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false)
//        id = getActivity()?.getIntent()?.getExtras()?.getInt("ITEM_USERID", 1)
//        username = getActivity()?.getIntent()?.getExtras()?.getString("ITEM_USERNAME", "tes")
        return  v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}