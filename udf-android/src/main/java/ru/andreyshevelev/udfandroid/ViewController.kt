package ru.andreyshevelev.udfandroid

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import ru.andreyshevelev.udf.*

open class ViewController<State, Action, News, View : BaseView<State, News>>(
    private val store: BaseStore<State, Action, News>
) :
    BaseViewController<State, Action, News>(store), LifecycleEventObserver {

    private var createdView: LifecycleOwner? = null

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate(source)
            Lifecycle.Event.ON_RESUME -> onResume(source as View)
            Lifecycle.Event.ON_PAUSE -> onPause()
            Lifecycle.Event.ON_DESTROY -> onDestroy(source)
        }
    }

    private fun onCreate(owner: LifecycleOwner) {
        this.createdView = owner
    }

    private fun onDestroy(owner: LifecycleOwner) {
        if (owner is Fragment) {
            owner.activity?.let { activity ->
                if (activity.isFinishing) {
                    destroy(owner)
                } else {
                    var anyParentIsRemoving = false
                    var parent = owner.parentFragment
                    while (!anyParentIsRemoving && parent != null) {
                        anyParentIsRemoving = parent.isRemoving
                        parent = parent.parentFragment
                    }
                    if (owner.isRemoving || anyParentIsRemoving) {
                        destroy(owner)
                    }
                }
            }
        }
        if (owner is Activity) {
            if (owner.isFinishing) {
                destroy(owner)
            }
        }
    }

    private fun destroy(owner: LifecycleOwner) {
        if (owner == this.createdView) {
            onDestroy()
        }
    }

    override fun scope(): CoroutineScope? {
        return createdView?.lifecycleScope
    }
}