package `in`.webxstudio.android.quoteiko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ChangeQuote(view: View) {
        Log.d(TAG,"Change Quote Clicked")
    }
    fun ChangeFontStyle(view: View) {
        Log.d(TAG,"Change FontStyle Clicked")
    }
    fun ChangeImage(view: View) {
        Log.d(TAG,"Change Image Clicked")
    }
}