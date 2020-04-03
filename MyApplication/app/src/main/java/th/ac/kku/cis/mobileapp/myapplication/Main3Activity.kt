package th.ac.kku.cis.mobileapp.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val name = getIntent().getExtras()!!.getString("name")
        val namell = getIntent().getExtras()!!.getString("objectIdb")
        val pice = getIntent().getExtras()!!.getString("pice")

        u.text = name
        obj.text = namell
        p.text = pice

        button7.setOnClickListener {
            startActivity(Intent(this@Main3Activity, MainActivity::class.java))
        }
    }
}
