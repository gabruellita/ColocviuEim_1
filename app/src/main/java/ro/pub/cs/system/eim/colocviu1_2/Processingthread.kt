package ro.pub.cs.system.eim.colocviu1_2

import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import java.util.Date

class Processingthread(private val context: Context, sum: Int) :
    Thread() {
    private var isRunning = true

    private val suma: Int
    init {suma = sum}


    override fun run() {
        Log.d(
            "[Processing Thread]",
            "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid()
        )
        while (isRunning) {
            sendMessage()
            sleep()
        }
        Log.d("[Processing Thread]", "Thread has stopped!")
    }

    private fun sendMessage() {
        val intent = Intent()
        intent.putExtra(
            "broadcast_receiver_extra",
            Date(System.currentTimeMillis()).toString() + " " + suma)
        context.sendBroadcast(intent)
    }

    private fun sleep() {
        try {
            sleep(2000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }
}