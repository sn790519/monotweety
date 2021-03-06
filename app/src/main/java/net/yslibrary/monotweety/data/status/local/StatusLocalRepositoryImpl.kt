package net.yslibrary.monotweety.data.status.local

import com.twitter.sdk.android.core.models.Tweet
import net.yslibrary.monotweety.base.di.UserScope
import rx.Completable
import rx.Observable
import rx.lang.kotlin.BehaviorSubject
import javax.inject.Inject

/**
 * Created by yshrsmz on 2016/10/02.
 */
@UserScope
class StatusLocalRepositoryImpl @Inject constructor() : StatusLocalRepository {

  val previousTweetSubject = BehaviorSubject<Tweet?>(null)

  override fun clear(): Completable {
    return Completable.fromAction {
      previousTweetSubject.onNext(null)
    }
  }

  override fun getPrevious(): Observable<Tweet?> {
    return previousTweetSubject.asObservable()
  }

  override fun update(tweet: Tweet): Completable {
    return Completable.fromAction {
      previousTweetSubject.onNext(tweet)
    }
  }
}