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

        Log.d(TAG, "Intent = $intent")
        Log.d(TAG, "Extras = ${intent.extras}")
        Log.d(TAG, "URI = ${intent.data}")

        val userId = intent.getStringExtra("userId")
        val screen = intent.getStringExtra("screen")
        val deepLink = intent.getStringExtra("deep_link")

        Log.d(TAG, "userId = $userId")
        Log.d(TAG, "screen = $screen")
        Log.d(TAG, "deep_link = $deepLink")

    }
}