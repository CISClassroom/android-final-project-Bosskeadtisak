package th.ac.kku.cis.mobileapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference

    var topost: MutableList<pice>? = null
    lateinit var adapter: itemadapter
    private var listViewItems: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        mDatabase = FirebaseDatabase.getInstance().reference

        val username = getIntent().getExtras()!!.getString("username")
        val email = getIntent().getExtras()!!.getString("emailAddress")
        val objectId = getIntent().getExtras()!!.getString("objectId")

        textView.text = username


        listViewItems = findViewById<View>(R.id.listpice) as ListView
        topost = mutableListOf<pice>()
        adapter = itemadapter(this, topost!!)
        listViewItems!!.setAdapter(adapter)
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listpice.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Main2Activity::class.java)
            val selectedItem = parent.getItemAtPosition(position) as pice
            intent.putExtra("pice", selectedItem.pice.toString())
//            intent.putExtra("objectIdp", selectedItem.objectIdp.toString())
            startActivity(intent)
        }

    }
    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("pice"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }

    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        // Check if current database contains any collection
        if (items.hasNext()) {


            // check if the collection has any to do items or not
            while (items.hasNext()) {
                // get current item
                val currentItem = items.next()
                val map = currentItem.getValue() as HashMap<String, Any>
                // add data to object

                val todoItem = pice.create()
                todoItem.pice = map.get("pice") as String
                todoItem.objectIdp = map.get("objectIdp") as String
                topost!!.add(todoItem);

            }

            adapter.notifyDataSetChanged()
        }
    }
}