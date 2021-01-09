package com.example.basicviews_dunets_l13

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.example.basicviews_dunets_l13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var n = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        binding.tvTitle.text = getString(R.string.n_value_text, n)
        setListeners()
    }

    private fun setListeners() {
        binding.tvStart.setOnClickListener {
            animateToKeyframeTwo()
        }
    }

    // Using ConstraintSet() for simple animation
    private fun animateToKeyframeTwo() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_main_second_frame)

        // It just looks nicer, when the text disappears first
        binding.tvTitle.text = ""
        TransitionManager.beginDelayedTransition(binding.root)
        constraintSet.applyTo(binding.root)

        // PB lasts (n+1) seconds, because (n+1)/10 seconds isn't enough for animations
        Handler(Looper.getMainLooper()).postDelayed(
            { animateToKeyframeOne() },
            ((n + 1) * 1000).toLong()
        )
    }

    // Using ConstraintSet() for simple animation
    private fun animateToKeyframeOne() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this, R.layout.activity_main)

        n++
        binding.tvTitle.text = getString(R.string.n_value_text, n)
        TransitionManager.beginDelayedTransition(binding.root)
        constraintSet.applyTo(binding.root)
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}