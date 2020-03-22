package com.example.myapplogin_fragment


import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.login_facebook.R
import com.bumptech.glide.request.RequestOptions
import com.example.login_facebook.Add_to_cart
import kotlinx.android.synthetic.main.fragment_detail_product.*
import android.R.attr.data
import android.content.Intent
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.FragmentManager
import com.example.login_facebook.chat_massager
import com.example.login_facebook.profile
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass.
 */
class detail_product : Fragment() {

    private var title:String?=null
    private var detail:String?=null
    private var image:String?=null
    private var url:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(com.example.login_facebook.R.layout.fragment_detail_product, container, false)
        val layout_title =view?.findViewById<TextView>(com.example.login_facebook.R.id._title)
//        val layout_detail =view?.findViewById<TextView>(com.example.login_facebook.R.id._det)
        val layout_image =view.findViewById<ImageView>(com.example.login_facebook.R.id.imageView)
        //val send_massage=view.findViewById<EditText>(R.id.send_mas)


        layout_title?.text = this.title
        //layout_detail?.text = this.detail

        Glide.with(this)
            .load(image)
            .into(layout_image)

        val test = Glide.with(this)
            .load(image)
            .into(layout_image)


        val alert_det = view?.findViewById<TextView>(com.example.login_facebook.R.id.det_add)

        alert_det?.text = this.detail
        //val btn4 = view.findViewById<Button>(R.id.f_add_cart)
        //val get_edittext = view.findViewById<EditText>(R.id.send_mas)
        val chat_mas =view?.findViewById<TextView>(com.example.login_facebook.R.id.chat_mas)
        chat_mas!!.setOnClickListener {
            val profile = chat_massager()//.newInstance(user!!.photoUrl.toString(),user!!.displayName.toString())
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, profile,"fragment_profile")
            transaction.addToBackStack("fragment_profile")
            transaction.commit()
        }


        return view
    }

    fun newInstance(title: String,detail: String,image:String,url:String): detail_product {
        val fragment = detail_product()
        val bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("detail", detail)
        bundle.putString("image", image)
        bundle.putString("url", url)

        fragment.setArguments(bundle)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var bundle = arguments
        if (bundle != null) {
            title = bundle.getString("title").toString()
            detail = bundle.getString("detail").toString()
            image = bundle.getString("image").toString()
        }
    }


}
