package com.example.login_facebook


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplogin_fragment.detail_product

/**
 * A simple [Fragment] subclass.
 */
class Add_to_cart : Fragment() {
    private var detail:String?=null
    private var image:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_to_cart, container, false)
        val layout_detail =view?.findViewById<TextView>(R.id.det_add)
        val layout_image =view.findViewById<ImageView>(R.id.image_add)


        layout_detail?.text = this.detail

        Glide.with(this)
            .load(image)
            .into(layout_image)

        return view
    }

    fun newInstance2(detail: String,image:String): detail_product {
        val fragment = detail_product()
        val bundle = Bundle()
        bundle.putString("detail", detail)
        bundle.putString("image", image)

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bundle = arguments
        if (bundle != null) {
            detail = bundle.getString("detail").toString()
            image = bundle.getString("image").toString()
        }
    }
}
