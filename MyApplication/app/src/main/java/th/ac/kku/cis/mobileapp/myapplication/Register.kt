package th.ac.kku.cis.mobileapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import java.net.URI.create

class Register : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mDatabase = FirebaseDatabase.getInstance().reference

       button3.setOnClickListener {

           val Username = editText3.text.toString()
           val Password = editText4.text.toString()
           val password1 = editText5.text.toString().trim { it <= ' ' }
           val email1 = editText6.text.toString()

           if (Username.isEmpty()) {
               editText3.error = "กรุณาใส่ผู้ใช้"
               return@setOnClickListener
           }
           if (Password.isEmpty()||password1.isEmpty()) {
               editText4.error = "กรุณาใส่รหัสผ่าน"
               editText5.error = "กรุณาใส่ยืนยันรหัสผ่าน"
               return@setOnClickListener
           }
           if (password1.isEmpty()) {
               editText.error = "กรุณาใส่ชื่อผู้ใช้"
               return@setOnClickListener
           }

           var  todoItem = customer.create()
           val newItem = mDatabase.child("customer").push()
           todoItem.objectId = newItem.key
           todoItem.username = Username
           todoItem.password = Password
           todoItem.emailAddress = email1
           newItem.setValue(todoItem)
           Toast.makeText(this@Register,"ลงทะเบียนสำเร็จ!.", Toast.LENGTH_SHORT).show()
           finish()

//           savedata()
       }

    }
    private fun savedata(){
        val Username = editText3.text.toString()
        val Password = editText4.text.toString()
        val password1 = editText5.text.toString().trim { it <= ' ' }
        val email1 = editText6.text.toString()

        if (Username.isEmpty()) {
            editText3.error = "กรุณาใส่ผู้ใช้"
            return
        }
        if (Password.isEmpty()||password1.isEmpty()) {
            editText4.error = "กรุณาใส่รหัสผ่าน"
            editText5.error = "กรุณาใส่ยืนยันรหัสผ่าน"
            return
        }
        if (password1.isEmpty()) {
            editText.error = "กรุณาใส่ชื่อผู้ใช้"
            return
        }
        mDatabase.child("customer").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val customer = dataSnapshot.children.iterator()
                if(customer.hasNext()){
                    var i=0
                    while (customer.hasNext()){
                        val customerItem = customer.next().getValue() as HashMap<String, Any>
                        if (customerItem.get("username") == Username || customerItem.get("emailAddress") == email1){
                            i+=1
                        }
                    }
                    if(i==0){
                        if (Password==password1){
                            val Customer = th.ac.kku.cis.mobileapp.myapplication.customer.create()
                            val newItem = mDatabase.child("customer").push()
                            Customer.username=Username
                            Customer.password=Password
                            Customer.emailAddress=email1
                            Customer.objectId = newItem.key
                            newItem.setValue(customer)
                            Toast.makeText(this@Register,"ลงทะเบียนสำเร็จ!.", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this@Register,"กรุณาใส่รหัสให้ตรงกัน!", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@Register,"ชื่อผู้ใช หรือ E-mail มีการใช้งานแล้ว!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }
    }

