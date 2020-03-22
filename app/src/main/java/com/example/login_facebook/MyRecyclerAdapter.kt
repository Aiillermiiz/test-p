import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.login_facebook.R
import com.example.myapplogin_fragment.detail_product
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class MyRecyclerAdapter(fragmentActivity: FragmentActivity, val dataSource: JSONArray) : RecyclerView.Adapter<MyRecyclerAdapter.Holder>() {

    private val thiscontext : Context = fragmentActivity.baseContext
    private val thisActivity = fragmentActivity

    class Holder(view : View) : RecyclerView.ViewHolder(view) {
        private val View = view;

        lateinit var layout : LinearLayout
        lateinit var titleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var image: ImageView
        lateinit var url: ImageView

        fun Holder(){

            layout = View.findViewById<View>(R.id.item) as LinearLayout
            titleTextView = View.findViewById<View>(R.id.tv_name) as TextView
            detailTextView = View.findViewById<View>(R.id.tv_description) as TextView
            image = View.findViewById<View>(R.id.imgV) as ImageView
            url = View.findViewById(R.id.tv_url) as ImageView

        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {


        holder.Holder()
        holder.titleTextView.setText( dataSource.getJSONObject(position).getString("title").toString() )
        holder.detailTextView.setText( dataSource.getJSONObject(position).getString("description").toString() )
        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.image)
        Glide.with(thiscontext)
            .load(dataSource.getJSONObject(position).getString("url").toString())
            .into(holder.url)

        holder.layout.setOnClickListener{
            val mRootRef = FirebaseDatabase.getInstance().getReference()

            //อ้างอิงไปที่ path ที่เราต้องการจะจัดการข้อมูล ตัวอย่างคือ users และ messages
            val mUsersRef = mRootRef.child("users")
            val mMessagesRef = mRootRef.child("messages")
            val mMessagesRef2 = mRootRef.child("data")
            var show_data = dataSource.getJSONObject(position).getString("title").toString()
//-----------------------------------------------------Date--------------------------------------------------

            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
            val formatedDate = formatter.format(date)
            val date_time = formatedDate

//-------------------------------------------------------------------------------------------------------------
            val key = mMessagesRef.push().key
            val postValues: HashMap<String, Any> = HashMap()
            postValues["username"] = show_data
            postValues["text"] = date_time

            val childUpdates: MutableMap<String, Any> = HashMap()

            childUpdates["$key"] = postValues

            mMessagesRef2.updateChildren(childUpdates)
//-----------------------------------------------Chang fragment--------------------------------------------------

           // Toast.makeText(thiscontext,holder.titleTextView.text.toString(),Toast.LENGTH_SHORT).show()
            val send_img:String = dataSource.getJSONObject(position).getString("image").toString()
            val send_url:String = dataSource.getJSONObject(position).getString("url").toString()
            val _detail_product = detail_product().newInstance(holder.titleTextView.text.toString(),holder.detailTextView.text.toString(),send_img,send_url)
            val frgMng = thisActivity.supportFragmentManager
            val transaction : FragmentTransaction = frgMng!!.beginTransaction()
            transaction.replace(R.id.layout, _detail_product,"_detail_product")
            transaction.addToBackStack("_detail_product")
            transaction.commit()


        }




    }


}
