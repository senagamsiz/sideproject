package nl.sena.sideproject.util

import android.view.View
import com.airbnb.lottie.LottieAnimationView
import nl.sena.sideproject.R

fun LottieAnimationView.showLoadingAnimation() {
    this.setAnimation(R.raw.loading)
    this.visibility = View.VISIBLE
    this.playAnimation()
}

fun LottieAnimationView.showErrorAnimation() {
    this.setAnimation(R.raw.error)
    this.visibility = View.VISIBLE
    this.playAnimation()
}

fun LottieAnimationView.removeAnimation() {
    this.pauseAnimation()
    this.visibility = View.INVISIBLE
}