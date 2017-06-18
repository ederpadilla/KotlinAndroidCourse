package eder.padilla.kotlinandroidcourse.mediaplayer

import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast

import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.R.id.sbProgress
import eder.padilla.kotlinandroidcourse.Util
import eder.padilla.kotlinandroidcourse.mediaplayer.adapter.OnSongSelected
import eder.padilla.kotlinandroidcourse.mediaplayer.model.Song
import kotlinx.android.synthetic.main.activity_media_player.*
import kotlinx.android.synthetic.main.item_song.view.*

class MediaPlayerActivity : AppCompatActivity() ,OnSongSelected{


    var songList = ArrayList<Song>()

    val ACCESSSTORAGE = 30

    var songsAdapter : SongsAdapter = SongsAdapter(songList,this@MediaPlayerActivity,this)

    var mediaPlayer:MediaPlayer?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        checkPermmisonStorage()
        initRecView()
    }

    private fun initRecView() {
        recViewSongs.layoutManager = LinearLayoutManager(this@MediaPlayerActivity, LinearLayoutManager.VERTICAL, false)
        recViewSongs.setHasFixedSize(true)
        recViewSongs.adapter=songsAdapter
        var myThread = mySongTrack()
        myThread.start()
        loadUrlOnline()
        songsAdapter.notifyDataSetChanged()

    }

    fun loadUrlOnline(){
        songList.add(Song("001","Ahmed","http://server6.mp3quran.net/thubti/001.mp3"))
        songList.add(Song("002","Ahmed","http://server6.mp3quran.net/thubti/002.mp3"))
        songList.add(Song("003","Alex","http://server6.mp3quran.net/thubti/003.mp3"))
        songList.add(Song("004","Ahmed","http://server6.mp3quran.net/thubti/004.mp3"))
        songList.add(Song("005","Alex","http://server6.mp3quran.net/thubti/005.mp3"))

    }
    fun checkPermmisonStorage(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.
                    checkSelfPermission(this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),ACCESSSTORAGE)
                return
            }
        }

        //getSongsFromStorage()
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ACCESSSTORAGE -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               // LoadSong()
            } else {
                // Permission Denied
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT)
                        .show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
    override fun onSongSelected(song : Song) {
    Util.log("Entra al click de la actividad ${song.toString()}")
    }

    inner class mySongTrack : Thread{
        constructor() : super(){
        }

        override fun run() {
            while (true) {
                try {
                    Thread.sleep(1000)
                } catch (e: Exception) {
                }
                runOnUiThread{
                    if (mediaPlayer!=null){
                        sbProgress.progress = mediaPlayer!!.currentPosition
                    }

                }
            }
        }
    }
    fun   loadSongsFromStorage() {
        val allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"
        val cursor = contentResolver.query(allSongsURI, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    val songURL = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val SongAuthor = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val SongName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                    songList.add(Song(SongName, SongAuthor, songURL))
                } while (cursor.moveToNext())


            }
            cursor.close()


        }
    }
    inner class SongsAdapter(private val notesList: List<Song>, private var context: Context?, private val onSongSelected: OnSongSelected) : RecyclerView.Adapter<SongsAdapter.ViewHolderAdapter>(), View.OnClickListener {

        private val listener: View.OnClickListener? = null
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {

            context = parent.context
            val view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false)
            val viewholder = ViewHolderAdapter(view)
            return viewholder
        }

        override fun onClick(view: View) {
            listener?.onClick(view)
        }
        fun setOnClickListener(listener: View.OnClickListener) {Util.log("Entra al click del metodo raro")}

        override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
            val item = notesList[position]
            holder.bindTexts(item)
            holder.buttonPlay!!.setOnClickListener { view -> playSong(holder.songUrl,holder.buttonPlay) }
            holder.rootView.setOnClickListener { view -> onSongSelected.onSongSelected(notesList[position]) }

        }

        private fun playSong(url : String?,button : Button?) {
            if(button!!.text == "Stop"){
                mediaPlayer!!.stop()
                sbProgress.progress=0
                button.text = "Play"
            }else {
                mediaPlayer = MediaPlayer()
                try {
                    mediaPlayer!!.setDataSource(url)
                    mediaPlayer!!.prepare()
                    mediaPlayer!!.start()
                    button.text = "Stop"
                    sbProgress.max=mediaPlayer!!.duration
                } catch (ex: Exception) {
                }
            }

        }

        override fun getItemCount(): Int {
            return notesList.size
        }

        inner class ViewHolderAdapter(internal var rootView: View) : RecyclerView.ViewHolder(rootView) {
            var buttonPlay : Button?=null
            var songUrl : String? = null
            fun bindTexts(song : Song) {
                itemView?.let{
                    with(itemView) {
                        tvSongName?.text = song.title
                        tvAuthor?.text = (song.authorName)
                        buttonPlay = buPlay
                        songUrl = song.songUrl
                    }
                }
            }

        }
    }
}
