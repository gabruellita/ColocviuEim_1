package ro.pub.cs.system.eim.colocviu1_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log

class Colocviu1_2SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_2_main)

        var suma = intent.getIntExtra("sum", 0)

        setResult(RESULT_OK)

        Log.d("SecondaryActivity", "The sum is: $suma")

        finish()
    }
}