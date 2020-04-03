package th.ac.kku.cis.mobileapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_main_page.*

class admin : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        mDatabase = FirebaseDatabase.getInstance().reference


        button5.setOnClickListener {
            savedata()
        }
    }
    private fun savedata(){
        var name = name_et.text.toString().trim()



        if (name.isEmpty()){
            name_et.error = "กรุณาใสชื่อโพส"
            return
        }

        var  todoItem = pice.create()
        val newItem = mDatabase.child("pice").push()
        todoItem.objectIdp = newItem.key
        todoItem.pice = name
        newItem.setValue(todoItem)
        finish()
    }
}