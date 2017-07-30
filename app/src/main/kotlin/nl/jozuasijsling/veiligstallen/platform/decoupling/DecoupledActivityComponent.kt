package nl.jozuasijsling.veiligstallen.platform.decoupling

import android.databinding.DataBindingComponent
import io.reactivex.Observable
import nl.jozuasijsling.veiligstallen.view.adapters.LifecycleEvent

class DecoupledActivityComponent(val platformCallbacks: Observable<PlatformCallback>,
                                 val lifecycle: Observable<LifecycleEvent>) : DataBindingComponent

