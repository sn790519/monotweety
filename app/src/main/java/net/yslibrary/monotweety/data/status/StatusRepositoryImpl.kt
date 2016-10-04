package net.yslibrary.monotweety.data.status

import com.twitter.sdk.android.core.models.Tweet
import net.yslibrary.monotweety.data.status.local.StatusLocalRepository
import net.yslibrary.monotweety.data.status.remote.StatusRemoteRepository
import rx.Completable
import rx.Observable
import timber.log.Timber

/**
 * Created by yshrsmz on 2016/09/30.
 */
class StatusRepositoryImpl(private val remoteRepository: StatusRemoteRepository,
                           private val localRepository: StatusLocalRepository) : StatusRepository {

  override fun updateStatus(status: String, inReplyToStatusId: Long?): Completable {
    return remoteRepository.update(status, inReplyToStatusId)
        .doOnSuccess {
          Timber.d("status updated: $it")
        }
        .flatMapCompletable { localRepository.update(it) }
  }

  override fun previousStatus(): Observable<Tweet?> {
    return localRepository.getPrevious()
  }
}