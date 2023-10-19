package com.example.rdhomework25firebase

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.gms.common.SignInButton

class SplashFragment: Fragment() {

    private val animatorSet = AnimatorSet()
    private var singInButton: SignInButton? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_login_screen,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singInButton = view.findViewById(R.id.sing_in_button)
        val image: ImageView = view.findViewById(R.id.login_screen_image)
        startAnimation(image)

    }

    private fun startAnimation(image: ImageView){
        val scaleXAnimation = ObjectAnimator.ofFloat(image, View.SCALE_X, 0.5F, 1F)
        scaleXAnimation.repeatMode = ObjectAnimator.REVERSE
        scaleXAnimation.repeatCount = ObjectAnimator.INFINITE
        val scaleYAnimation = ObjectAnimator.ofFloat(image, View.SCALE_Y, 0.5F, 1F)
        scaleYAnimation.repeatMode = ObjectAnimator.REVERSE
        scaleYAnimation.repeatCount = ObjectAnimator.INFINITE
        animatorSet.playTogether(scaleXAnimation,scaleYAnimation)
        animatorSet.duration = 1000
        animatorSet.start()
    }
    private fun showSingInButton(){
        singInButton?.visibility = View.VISIBLE
        animatorSet.cancel()
    }

}