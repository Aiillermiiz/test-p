package com.example.myapprecyclerview


import MyRecyclerAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login_facebook.R
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 */
class Recycler_view : Fragment() {
    private var username : String? = null
    fun newInstance(username: String): Recycler_view {
        val fragment = Recycler_view()
        val bundle = Bundle()
        bundle.putString("show_username", username)
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)
        // Inflate the layout for this fragment
        lateinit var layout : LinearLayout

        val jsonString : String = loadJsonFromAsset("recipes.json", activity!!.baseContext).toString()
        val json = JSONObject(jsonString)
        val jsonArray = json.getJSONArray("recipes")
        val recyclerView: RecyclerView = view.findViewById(R.id.recyLayout)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)
        recyclerView.layoutManager = layoutManager
        val adapter = MyRecyclerAdapter(activity!!,jsonArray)
        recyclerView.adapter = adapter

        return view
    }
    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            username = bundle.getString("show_username").toString()
            //password = bundle.getString("password").toString()

        }
    }
    fun onItemClick() {

    }


}
