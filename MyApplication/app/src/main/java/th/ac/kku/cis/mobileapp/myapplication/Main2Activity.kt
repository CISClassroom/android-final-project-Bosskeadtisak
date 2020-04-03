package th.ac.kku.cis.mobileapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mDatabase = FirebaseDatabase.getInstance().reference

        val pice = getIntent().getExtras()!!.getString("pice")
//        val objectId = getIntent().getExtras()!!.getString("objectIdp")

        textView6.text = pice

        button6.setOnClickListener {
            var name = editText7.text.toString().trim()
            var num = editText8.text.toString().trim()
            var comfilm = editText9.text.toString().trim()

            if (name.isEmpty()){
                editText7.error = "\n" +
                        "Please enter name"
                return@setOnClickListener
            }
            if (num.isEmpty()){
                editText8.error = "\n" +
                        "Card number"
                return@setOnClickListener
            }
            if (comfilm.isEmpty()){
                editText9.error = "\n" +
                        "Card confirmation number"
                return@setOnClickListener
            }

            var  todoItem = buys.create()
            val newItem = mDatabase.child("buy").push()
            todoItem.objectIdb = newItem.key
            todoItem.name = name
            todoItem.numCard = num
            todoItem.confirmCard = comfilm
            newItem.setValue(todoItem)
            val intent = Intent(this@Main2Activity, Main3Activity::class.java)
            intent.putExtra("name",name.toString())
            intent.putExtra("pice",pice.toString())
            intent.putExtra("objectIdb",newItem.key.toString())
            startActivity(intent)

        }



    }
    private fun savedata_b(){
        var name = editText7.text.toString().trim()
        var num = editText8.text.toString().trim()
        var comfilm = editText9.text.toString().trim()

        if (name.isEmpty()){
            editText7.error = "กรุณาใสชื่อ"
            return
        }
        if (num.isEmpty()){
            editText8.error = "เลขการ์ด"
            return
        }
        if (comfilm.isEmpty()){
            editText9.error = "เลขยืนยันการ์ด"
            return
        }

        var  todoItem = buys.create()
        val newItem = mDatabase.child("buy").push()
        todoItem.objectIdb = newItem.key
        todoItem.name = name
        todoItem.numCard = num
        todoItem.confirmCard = comfilm
        newItem.setValue(todoItem)
        startActivity(Intent(this, Main3Activity::class.java))
    }

}
