package nl.jozuasijsling.veiligstallen.view.adapters

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.reactivex.subjects.PublishSubject
import nl.jozuasijsling.veiligstallen.view.adapters.LifecycleEvent.*
import nl.jozuasijsling.veiligstallen.view.adapters.LifecycleListener.stream

object LifecycleListener : Application.ActivityLifecycleCallbacks {

    internal val stream = PublishSubject.create<Pair<Activity, LifecycleEvent>>()!!

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        stream.onNext(activity to Create(bundle))
    }

    override fun onActivityStarted(activity: Activity) {
        stream.onNext(activity to Start())
    }

    override fun onActivityResumed(activity: Activity) {
        stream.onNext(activity to Resume())
    }

    override fun onActivityPaused(activity: Activity) {
        stream.onNext(activity to Pause())
    }

    override fun onActivityStopped(activity: Activity) {
        stream.onNext(activity to Stop())
    }

    override fun onActivityDestroyed(activity: Activity) {
        stream.onNext(activity to Destroy())
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {
        // Ignore
    }
}


fun Activity.lifecycleStream() = stream.hide()
        .filter { (activity, _) -> activity == this }
        .map { (_, state) -> state }
        .takeUntil { it is Destroy }!!


sealed class LifecycleEvent {
    class Create(val bundle: Bundle?) : LifecycleEvent()
    class Start : LifecycleEvent()
    class Resume : LifecycleEvent()
    class Pause : LifecycleEvent()
    class Stop : LifecycleEvent()
    class Destroy : LifecycleEvent()
}