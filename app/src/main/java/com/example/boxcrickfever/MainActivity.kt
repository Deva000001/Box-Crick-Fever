package com.example.boxcrickfever

import android.os.Bundle
import android.media.MediaPlayer
import android.os.Build
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var teamAName: EditText
    private lateinit var teamBName: EditText
    private lateinit var teamAScoreDisplay: TextView
    private lateinit var teamBScoreDisplay: TextView
    private var scoreA: Int = 0
    private var wicketsA: Int = 0
    private var scoreB: Int = 0
    private var wicketsB: Int = 0
    private var isTeamANameSet: Boolean = false
    private var isTeamBNameSet: Boolean = false

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var who: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.blue)
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.wicket)
        who = MediaPlayer.create(this, R.raw.whooo)

        // Initialize views
        teamAName = findViewById(R.id.teamAName)
        teamBName = findViewById(R.id.teamBName)
        teamAScoreDisplay = findViewById(R.id.teamAScoreDisplay)
        teamBScoreDisplay = findViewById(R.id.teamBScoreDisplay)

        // Save team names button
        findViewById<Button>(R.id.btnSaveTeamNames).setOnClickListener {
            saveTeamNames()
        }

        // Reset button
        findViewById<Button>(R.id.btnReset).setOnClickListener {
            resetAll()
        }

        // Set click listeners for Team A buttons
        findViewById<Button>(R.id.btnAdd1A).setOnClickListener { addScoreA(1) }
        findViewById<Button>(R.id.btnAdd2A).setOnClickListener { addScoreA(2) }
        findViewById<Button>(R.id.btnAdd3A).setOnClickListener { addScoreA(3) }
        findViewById<Button>(R.id.btnAdd4A).setOnClickListener { addScoreA(4) }
        findViewById<Button>(R.id.btnAdd6A).setOnClickListener { addScoreA(6) }
        findViewById<Button>(R.id.btnWicketA).setOnClickListener { addWicketA() }

        // Set click listeners for Team B buttons
        findViewById<Button>(R.id.btnAdd1B).setOnClickListener { addScoreB(1) }
        findViewById<Button>(R.id.btnAdd2B).setOnClickListener { addScoreB(2) }
        findViewById<Button>(R.id.btnAdd3B).setOnClickListener { addScoreB(3) }
        findViewById<Button>(R.id.btnAdd4B).setOnClickListener { addScoreB(4) }
        findViewById<Button>(R.id.btnAdd6B).setOnClickListener { addScoreB(6) }
        findViewById<Button>(R.id.btnWicketB).setOnClickListener { addWicketB() }

        // Update initial display
        updateScoreDisplayA()
        updateScoreDisplayB()
    }

    private fun saveTeamNames() {
        if (!isTeamANameSet && teamAName.text.isNotEmpty()) {
            teamAName.isEnabled = false
            isTeamANameSet = true
        }
        if (!isTeamBNameSet && teamBName.text.isNotEmpty()) {
            teamBName.isEnabled = false
            isTeamBNameSet = true
        }
    }

    private fun resetAll() {
        scoreA = 0
        wicketsA = 0
        scoreB = 0
        wicketsB = 0
        isTeamANameSet = false
        isTeamBNameSet = false

        teamAName.isEnabled = true
        teamBName.isEnabled = true
        teamAName.text.clear()
        teamBName.text.clear()

        updateScoreDisplayA()
        updateScoreDisplayB()
    }

    // Methods for Team A
    private fun addScoreA(run: Int) {
        scoreA += run
        updateScoreDisplayA()
        whooo()
    }

    private fun addWicketA() {
        if (wicketsA < 10) {
            wicketsA += 1
            updateScoreDisplayA()

            playButtonClickSound()

        }
    }

    private fun updateScoreDisplayA() {
        teamAScoreDisplay.text = "Score: $scoreA/$wicketsA"
    }

    // Methods for Team B
    private fun addScoreB(run: Int) {
        scoreB += run
        updateScoreDisplayB()
        whooo()
    }

    private fun addWicketB() {
        if (wicketsB < 10) {
            wicketsB += 1
            updateScoreDisplayB()
            playButtonClickSound()
        }
    }

    private fun updateScoreDisplayB() {
        teamBScoreDisplay.text = "Score: $scoreB/$wicketsB"
    }

    private fun playButtonClickSound() {
        mediaPlayer.seekTo(0)  // Rewind to the beginning if it's already playing
        mediaPlayer.start()
    }

    private fun whooo() {
        who.seekTo(0)  // Rewind to the beginning if it's already playing
        who.start()
    }


}
