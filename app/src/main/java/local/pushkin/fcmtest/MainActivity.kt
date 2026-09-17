    package local.pushkin.fcmtest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    println("FCM token error: ${task.exception}")
                    return@addOnCompleteListener
                }

                val token = task.result
                println("FCM TOKEN: $token")
            }

        Log.d(TAG, "Intent = $intent")
        Log.d(TAG, "Extras = ${intent.extras}")
        Log.d(TAG, "URI = ${intent.data}")

        val userId = intent.getStringExtra("userId")
        val screen = intent.getStringExtra("screen")
        val deepLink = intent.getStringExtra("deep_link")

        Log.d(TAG, "userId = $userId")
        Log.d(TAG, "screen = $screen")
        Log.d(TAG, "deep_link = $deepLink")

        if (deepLink != null) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(deepLink)
                )
            )
        }
    }


    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")

        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")

        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        super.onDestroy()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart")

        super.onRestart()
    }
}