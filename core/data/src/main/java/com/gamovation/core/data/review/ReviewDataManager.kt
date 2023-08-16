package com.gamovation.core.data.review

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import com.gamovation.core.data.repository.OfflineUserInfoPreferencesRepository
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewDataManager @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val offlineUserInfoPreferencesRepository: OfflineUserInfoPreferencesRepository
) {
    private val manager = ReviewManagerFactory.create(applicationContext)


    private suspend fun isAvailableForReview(): Boolean = withContext(Dispatchers.IO) {
        offlineUserInfoPreferencesRepository.getIsAvailableForReview().firstOrNull() ?: true
    }

    suspend fun requestReviewInfo(activity: ComponentActivity, showDialog: () -> Unit) {
        Log.i("TAG", "requestReviewInfo: available ${isAvailableForReview()}")
        if (isAvailableForReview()) {
            showDialog()
            manager.requestReviewFlow().addOnCompleteListener { task ->
                Log.i("TAG", "requestReviewInfo: ${task.exception}")
                if (task.isSuccessful) {

                    val reviewInfo: ReviewInfo? = task.result
                    startReviewFlow(activity, reviewInfo)

                    CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                        offlineUserInfoPreferencesRepository.setIsAvailableForReview(false)
                    }
                }
            }
        }
    }

    private fun startReviewFlow(activity: ComponentActivity, reviewInfo: ReviewInfo?) {
        reviewInfo?.let { info ->
            manager.launchReviewFlow(activity, info).addOnCompleteListener {}
        }
    }
}