package com.xvadsan.blankmvp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment<P : BaseContract.Presenter> : Fragment() {

    @Inject
    lateinit var presenter: P

    private var isCommitsAllowed: Boolean = false
    private val resumeFragmentActions: MutableList<Runnable> by lazy { mutableListOf<Runnable>() }

    private var resultAction: Runnable? = null

    fun executeResumeFragmentAction(action: Runnable) {
        if (isCommitsAllowed) action.run() else resumeFragmentActions.add(action)
    }

    inline fun executeResumeFragmentAction(crossinline action: () -> Unit) = executeResumeFragmentAction(Runnable { action.invoke() })

    inline fun executeResultAction(crossinline action: () -> Unit) = executeResultAction(Runnable { action.invoke() })

    fun executeResultAction(action: Runnable) = if (isHidden.not()) action.run() else resultAction = action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()
    }

    override fun onPause() {
        isCommitsAllowed = false
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        isCommitsAllowed = true
        resumeFragmentActions.forEach(Runnable::run)
        resumeFragmentActions.clear()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden.not() && resultAction != null) {
            resultAction?.run()
            resultAction = null
        }
    }

    protected abstract fun createComponent()

    override fun onDestroyView() {
        presenter.stop()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}