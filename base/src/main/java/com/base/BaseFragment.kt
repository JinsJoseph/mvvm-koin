@file:Suppress("UNCHECKED_CAST")

package com.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.*
import com.widget.Boast

/**
 * Created by Kaz on 09:48 8/20/18
 */
abstract class BaseFragment<T : ViewDataBinding, V : ViewModelB<*>> : Fragment(),
        ViewTreeObserver.OnGlobalLayoutListener {

    var rootView: View? = null
    var binding: T? = null

    protected abstract fun getViewModel(): V

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected abstract fun getBindingVariable(): Int

    /**
     * update screen
     *
     * @param savedInstanceState
     */
    protected abstract fun updateUI(savedInstanceState: Bundle?)

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    override fun onGlobalLayout() {
        rootView!!.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutId = getLayoutId()
        if (rootView != null) {
            val parent = rootView!!.parent as ViewGroup
            parent.removeView(rootView)
        } else {
            try {
                binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
                rootView = if (binding != null) {
                    binding!!.root
                } else {
                    inflater.inflate(layoutId, container, false)
                }
                rootView!!.viewTreeObserver.addOnGlobalLayoutListener(this)

                binding!!.setVariable(getBindingVariable(), getViewModel())
                binding!!.executePendingBindings()

                updateUI(savedInstanceState)
            } catch (e: InflateException) {
                e.printStackTrace()
            }
        }
        return rootView
    }

    @Throws
    open fun openFragment(
            resId: Int,
            fragmentClazz: Class<*>,
            args: Bundle?,
            addBackStack: Boolean
    ) {
        val tag = fragmentClazz.simpleName
        try {
            val isExisted = childFragmentManager.popBackStackImmediate(tag, 0)    // IllegalStateException
            if (!isExisted) {
                val fragment: Fragment
                try {
                    fragment = (fragmentClazz as Class<Fragment>).newInstance().apply { arguments = args }

                    val transaction = childFragmentManager.beginTransaction()
                    //transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                    transaction.add(resId, fragment, tag)

                    if (addBackStack) {
                        transaction.addToBackStack(tag)
                    }
                    transaction.commitAllowingStateLoss()

                } catch (e: java.lang.InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws
    open fun openFragment(
            resId: Int, fragmentClazz: Class<*>, args: Bundle?, addBackStack: Boolean,
            vararg aniInt: Int
    ) {
        val tag = fragmentClazz.simpleName
        try {
            val isExisted = childFragmentManager.popBackStackImmediate(tag, 0)    // IllegalStateException
            if (!isExisted) {
                val fragment: Fragment
                try {
                    fragment = (fragmentClazz as Class<Fragment>).newInstance().apply { arguments = args }

                    val transaction = childFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(aniInt[0], aniInt[1], aniInt[2], aniInt[3])

                    transaction.add(resId, fragment, tag)

                    if (addBackStack) {
                        transaction.addToBackStack(tag)
                    }
                    transaction.commitAllowingStateLoss()

                } catch (e: java.lang.InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun toast(msg: String) {
        context?.let {
            Boast.makeText(it, msg).show()
        }
    }

    fun toast(msg: String, duration: Int, cancelCurrent: Boolean) {
        context?.let {
            Boast.makeText(it, msg, duration).show(cancelCurrent)
        }
    }

    fun showDialog() {
        activity?.let {
            if (it is BaseActivity<*, *>) {
                it.showDialog()
            }
        }
    }

    fun hideDialog() {
        activity?.let {
            if (it is BaseActivity<*, *>) {
                it.hideDialog()
            }
        }
    }

    fun hideKeyboard() {
        activity?.let {
            if (it is BaseActivity<*, *>) {
                it.hideKeyboard()
            }
        }
    }

    fun hideKeyboardOutSide(view: View) {
        activity?.let {
            if (it is BaseActivity<*, *>) {
                it.hideKeyboardOutSide(view)
            }
        }
    }

    fun hideKeyboardOutSideText(view: View) {
        activity?.let {
            if (it is BaseActivity<*, *>) {
                it.hideKeyboardOutSideText(view)
            }
        }
    }

    fun onBackPressed() {
        activity?.onBackPressed()
    }

    fun finish() {
        activity?.finish()
    }
}