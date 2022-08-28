package com.example.bai1

import android.annotation.SuppressLint
import android.app.Activity
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bai1.databinding.FragmentBt1Binding

class Bt1Fragment:Fragment() {
    private lateinit var binding: FragmentBt1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBt1Binding.inflate(layoutInflater)
        binding.btnStart.setOnClickListener {
            MyAsyncTask(requireActivity()).execute()
        }
        return binding.root
    }

    private class MyAsyncTask(private val context: Activity) : AsyncTask<Int, Int, Int>(){
        private val tvProgress = context.findViewById(R.id.tvLoading) as TextView
        private val progressBar = context.findViewById(R.id.pbLoading) as ProgressBar
        override fun doInBackground(vararg p0: Int?): Int {
            for (i in 1..100){
                SystemClock.sleep(100)
                publishProgress(i)
            }
            return 1
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val message = Message()
            message.what = values[0]!!
            handler.sendMessage(message)

        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)

        }
        override fun onPreExecute() {
            super.onPreExecute()
        }
        var handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                val position = msg.what
                progressBar!!.progress = position
                tvProgress!!.text = position.toString()
            }
        }

    }

}