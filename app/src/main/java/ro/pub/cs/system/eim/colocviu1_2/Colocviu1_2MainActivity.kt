package ro.pub.cs.system.eim.colocviu1_2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Colocviu1_2MainActivity : AppCompatActivity() {
    lateinit var addBtn : Button
    lateinit var edittext : EditText
    lateinit var viewtext : TextView
    lateinit var compbtn : Button
    var ok = 1;
    var sum = 0;

//    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        val messageBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.let {
                    Log.d("[Message]", it.action.toString())
                    Log.d("[Message]", it.getStringExtra("broadcast_receiver_extra").toString())
                }
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_2_main)

        edittext = findViewById<EditText>(R.id.editText1)
        viewtext = findViewById<TextView>(R.id.textview)
        addBtn = findViewById<Button>(R.id.btnAdd)
        addBtn.setOnClickListener {
            val text = edittext.text.toString()
            val sum =sum + text.toInt()
            if (ok == 1) {
                viewtext.append(text)
                ok = 0
            } else
            viewtext.append("+$text")
            edittext.text.clear()
        }

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG).show()
            }
            else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "The activity returned with result CANCELED", Toast.LENGTH_LONG).show()
            }
        }
        compbtn = findViewById<Button>(R.id.btnCompute)
        compbtn.setOnClickListener {
            val intent = Intent(this, Colocviu1_2SecondaryActivity::class.java)
            intent.putExtra("sum", sum)
            activityResultsLauncher.launch(intent)
        }

        if(sum > 10){
            val intent = Intent(this, Colocviu1_2Service::class.java)
            intent.putExtra("viewtext", sum)
            startService(intent)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("viewtext", viewtext.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("viewtext")) {
            viewtext.text = savedInstanceState.getString("viewtext")

        }
    }
}