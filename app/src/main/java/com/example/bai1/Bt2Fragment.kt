package com.example.bai1

import android.annotation.SuppressLint
import android.app.Activity
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bai1.databinding.FragmentBt2Binding
import java.util.*

class Bt2Fragment:Fragment() {
  private lateinit var binding: FragmentBt2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBt2Binding.inflate(layoutInflater)
        binding.btnStart.setOnClickListener {
            MyAsyncTask(requireActivity()).execute()
        }
        return binding.root
    }

    private class MyAsyncTask(private val context: Activity) : AsyncTask<Int, Int, ArrayList<Int>>(){
        private val edtInput = context.findViewById<EditText>(R.id.edtInput)
        private val llLeft = context.findViewById<LinearLayout>(R.id.llLeft)
        private val llRight = context.findViewById<LinearLayout>(R.id.llRight)
        private var n :Int =0
        private var random  = Random()
        init {
            n = edtInput.text.toString().toInt()
        }

        override fun onPostExecute(result: ArrayList<Int>?) {
            super.onPostExecute(result)
            val thread = Thread(Runnable {
                for (i in 0..result!!.size-1){
                    SystemClock.sleep(100)
                    val message = Message()
                    message.what = result.get(i)
                    handler.sendMessage(message)
//                    handler.post {
//                        val button = Button(context)
//                        button.width =100
//                        button.height = 50
//                        button.text = result.get(i).toString()
//                        llRight.addView(button)
//                    }
                }
            }).start()
        }

        override fun doInBackground(vararg p0: Int?): ArrayList<Int> {
            var step = 1
            val array = arrayListOf<Int>()
            while (isCancelled == false && step <n){
                step++
                SystemClock.sleep(100)
                val x = random.nextInt(100) +1
                publishProgress(x)
                if (isPrime(x)){
                    array.add(x)
                }
            }
            return array

        }
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val button = Button(context)
            button.width =100
            button.height = 50
            button.text = values[0].toString()
            llLeft.addView(button)
        }
        override fun onPreExecute() {
            super.onPreExecute()
            llLeft.removeAllViews()
            llRight.removeAllViews()
        }

        fun isPrime(x :Int):Boolean{
            if (x<2) return false
            for (i in 2..Math.sqrt(x.toDouble()).toInt()){
                if (x%i ==0) return false
            }
            return true
        }
        var handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                val position = msg.what
                val button = Button(context)
                button.width =100
                button.height = 50
                button.text = position.toString()
                llRight.addView(button)
            }
        }

    }

}