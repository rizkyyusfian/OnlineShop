package com.ubaya.onlineshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_profile.*
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderHistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var orders:ArrayList<Order> = ArrayList()
    var v:View ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()

        val q = Volley.newRequestQueue(context)
        val url = "http://ubaya.prototipe.net/nmp160418112/getorderhistory.php"
        var stringRequest = object: StringRequest(Request.Method.POST, url,
            {
                Log.d("apiresultORDER", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    for(i in 0 until data.length()) {
                        val playObj = data.getJSONObject(i)
                        val order = Order(
                            playObj.getInt("idorderhistory"),
                            playObj.getString("tanggalorder"),
                            playObj.getInt("totalitem"),
                            playObj.getInt("quantity"),
                            playObj.getInt("subtotal")
                        )
                        orders.add(order)
                    }
                    updateList()
                    Log.d("apiresultORDER", orders.toString())
                }
            },
            {
                Log.e("apiresultORDER", it.message.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                var params = HashMap<String, String>()
                params.put("id", Global.userid)
                return params
            }
        }
        q.add(stringRequest)
        orders.clear() //AGAR ORDER TIDAK LOOPING TERUS
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_order_history, container, false)
        return v
    }

    fun updateList() {
        val lm: LinearLayoutManager = LinearLayoutManager(activity)
        var recyclerView = v?.findViewById<RecyclerView>(R.id.orderView)
        recyclerView?.layoutManager = lm
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = OrderAdapter(orders)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderHistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderHistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}