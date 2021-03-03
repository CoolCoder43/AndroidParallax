package com.example.scrolltest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class YoutubeActivity : AppCompatActivity() {
     var vid_ID = "FSqNp2F5YRo"
     lateinit var yt_player: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        createYoutubePlayer()
    }
//https://www.youtube.com/watch?v=mvUFQM-dL9Y

    fun createYoutubePlayer() {
        yt_player = findViewById(R.id.youtube_player_view)
        lifecycle.addObserver(yt_player)
        yt_player.enableAutomaticInitialization = false

        val frame_options = IFramePlayerOptions.Builder().controls(0).rel(0).ivLoadPolicy(0).build()
        yt_player.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = vid_ID
                youTubePlayer.loadVideo(videoId,0f)
            }
        },true, frame_options)

//        yt_player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = vid_ID
//                youTubePlayer.loadVideo(videoId,0f)
//            }
//        })

      val vw:ConstraintLayout = findViewById(R.id.transview)
    vw.setOnTouchListener(object : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (event?.action) {
                MotionEvent.ACTION_DOWN ->
                    toggle()
            }
            return v?.onTouchEvent(event) ?: true
        }
    })


}

    fun onTapDone( v:View){
        finish()
    }


    private fun toggle() {
        val btn:Button = findViewById(R.id.button3)

       if (!btn.isVisible){
          btn.visibility = View.VISIBLE
           btn.alpha = 0.0F
           btn.animate().translationY(-40.0F).alpha(1.0F).setListener(null)
       }else{
           btn.animate().translationY(0.0F).alpha(0.0F).setListener(null)
           btn.visibility = View.INVISIBLE
       }
    }


    override fun onDestroy() {
        super.onDestroy()
        yt_player.release()
    }
}




