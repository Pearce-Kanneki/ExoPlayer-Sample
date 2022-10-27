package com.kanneki.exoplay

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.kanneki.exoplay.databinding.ActivityMainBinding

/**
 *
 * @see <a href="https://github.com/google/ExoPlayer">GitHub</a>
 * @see <a href="https://exoplayer.dev/">Doc</a>
 */
class MainActivity : AppCompatActivity() {

    //
    private val videoUrl = "https://static.videezy.com/system/resources/previews/000/050/575/original/alb_glob0401_1080p_24fps.mp4"

    private lateinit var play: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        play = ExoPlayer.Builder(this).build()
        binding.viewPlay.player = play

        // 第1種寫法(簡單)
        val mediaItem = MediaItem.fromUri(videoUrl)

        // 第2種寫法(詳細)
//        val mediaItem = MediaItem.Builder()
//            .setUri(videoUrl)
//            .setMimeType(MimeTypes.VIDEO_MP4)
//            .build()

        play.apply {
            setMediaItem(mediaItem)
            prepare()
            play()

            //region === ExoPlayer Listener ===
            addListener(object : Player.Listener {
                // https://exoplayer.dev/doc/reference/com/google/android/exoplayer2/Player.Listener.html
                // https://blog.csdn.net/tst116/article/details/118102059

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    Log.d("pearce", "onIsPlayingChanged isPlay: $isPlaying")
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    
                    when(playbackState) {
                        ExoPlayer.STATE_IDLE -> {
                            Log.d("pearce", "onPlaybackStateChanged: STATE_IDLE")
                        }
                        ExoPlayer.STATE_BUFFERING -> {
                            Log.d("pearce", "onPlaybackStateChanged: STATE_BUFFERING")
                        }
                        ExoPlayer.STATE_READY -> {
                            Log.d("pearce", "onPlaybackStateChanged: STATE_READY")
                        }
                        ExoPlayer.STATE_ENDED -> {
                            Log.d("pearce", "onPlaybackStateChanged: STATE_ENDED")
                        }
                    }
                }
            })
            //endregion
        }

    }
}