package local.pushkin.fcmtest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SecondActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("SecondActivity", "Intent = $intent")
        Log.d("SecondActivity", "Extras = ${intent.extras}")
        Log.d("SecondActivity", "Extras = ${intent.data}")

        val userId = intent.getStringExtra("userId")
        val screen = intent.getStringExtra("screen")

        Log.d(TAG, "userId = $userId")
        Log.d(TAG, "screen = $screen")

    }
}