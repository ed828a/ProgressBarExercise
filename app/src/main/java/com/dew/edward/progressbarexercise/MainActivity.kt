package com.dew.edward.progressbarexercise

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var progressStatus = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myButton.setOnClickListener {
            var i = 0
            if (i == 0 || i == 10) {
                //make the progress bar visible
                firstBar.setVisibility(View.VISIBLE);
                firstBar.setMax(150);
                secondBar.setVisibility(View.VISIBLE);
            }else if ( i< firstBar.getMax() ) {
                //Set first progress bar value
                firstBar.setProgress(i);
                //Set the second progress bar value
                firstBar.setSecondaryProgress(i + 10);
            }else {
                firstBar.setProgress(0);
                firstBar.setSecondaryProgress(0);
                i = 0;
                firstBar.setVisibility(View.GONE);
                secondBar.setVisibility(View.GONE);
            }
            i = i + 10;
        }
        Thread(Runnable {
            while (progressStatus < firstBar.getMax()) {
                progressStatus += 1
                // Update the progress bar and display the
                //current value in the text view
                handler.post {
                    firstBar.setProgress(progressStatus)
                    textView.setText(progressStatus.toString() + "/" + firstBar.getMax())
                }
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(200)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }).start()
    }
}
