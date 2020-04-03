package th.ac.kku.cis.mobileapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDatabase = FirebaseDatabase.getInstance().reference
        button.setOnClickListener {
            val id = editText.text.toString().trim { it <= ' ' }
            val email = editText.text.toString().trim { it <= ' ' }
            val password = editText2.text.toString().trim { it <= ' ' }
            if (id.isEmpty()){
                editText.error = "\n" +
                        "Please enter the user."
                return@setOnClickListener
            }
            if (email.isEmpty()){
                editText.error = "\n" +
                        "Please enter the user."
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                editText2.error = "\n" +
                        "Please enter your password."
                return@setOnClickListener
            }
            mDatabase.child("customer")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val customer = dataSnapshot.children.iterator()
                        var i=0


                        if(customer.hasNext()){
                            while (customer.hasNext()){
                                val studentItem = customer.next().getValue() as HashMap<String, Any>
                                if (studentItem.get("username")==id || studentItem.get("emailAddress")==email && studentItem.get("password") == password){
                                    i=0
                                    val intent = Intent(this@MainActivity, MainPage::class.java)
                                    intent.putExtra("username",studentItem.get("username") as String)
                                    intent.putExtra("emailAddress",studentItem.get("emailAddress") as String)
                                    intent.putExtra("objectId",studentItem.get("objectId") as String)
                                    startActivity(intent)
                                    break
                                }else{
                                    i=1
                                }
                            }
                        }
                        if(i!=0 ){
                            return
                        }
                    }
                    override fun onCancelled(databaseError: DatabaseError) {
                    }
                })

        }
//        button4.setOnClickListener {
//            startActivity(Intent(this, admin::class.java))
//        }

        button2.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }
}
