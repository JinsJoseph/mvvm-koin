@file:Suppress("UNCHECKED_CAST")

package com.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.widget.Boast
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Kaz on 09:27 8/20/18
 */
abstract class BaseActivity<T : ViewDataBinding, V : ViewModelB<*>> : AppCompatActivity() {

    lateinit var binding: T
    lateinit var loading: AlertDialog
    lateinit var loading2: AlertDialog
    private var isCancelable = false
    private var isCancelable2 = false

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @LayoutRes
    open fun getLayoutIdLoading(): Int = -1

    open fun getThemResId(): Int = -1

    protected abstract fun getBindingVariable(): Int

    protected abstract fun updateUI(savedInstanceState: Bundle?)

    protected abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        updateUI(savedInstanceState)
        initDialog()
        initDialog2()
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.executePendingBindings()
        binding.setVariable(getBindingVariable(), getViewModel())
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    @Throws
    open fun openFragment(resId: Int, fragmentClazz: Class<*>, args: Bundle?, addBackStack: Boolean) {
        val tag = fragmentClazz.simpleName
        try {
            val isExisted = supportFragmentManager.popBackStackImmediate(tag, 0)    // IllegalStateException
            if (!isExisted) {
                val fragment: Fragment
                try {
                    fragment = (fragmentClazz as Class<Fragment>).newInstance().apply { arguments = args }

                    val transaction = supportFragmentManager.beginTransaction()
                    //transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                    transaction.add(resId, fragment, tag)

                    if (addBackStack) {
                        transaction.addToBackStack(tag)
                    }
                    transaction.commitAllowingStateLoss()

                } catch (e: InstantiationException) {
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
            val isExisted = supportFragmentManager.popBackStackImmediate(tag, 0)    // IllegalStateException
            if (!isExisted) {
                val fragment: Fragment
                try {
                    fragment = (fragmentClazz as Class<Fragment>).newInstance().apply { arguments = args }

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(aniInt[0], aniInt[1], aniInt[2], aniInt[3])

                    transaction.add(resId, fragment, tag)

                    if (addBackStack) {
                        transaction.addToBackStack(tag)
                    }
                    transaction.commitAllowingStateLoss()

                } catch (e: InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Show toast
     * @param msg
     */
    fun toast(msg: String) = Boast.makeText(this, msg).show()

    /**
     * Init dialog loading
     */
    private fun initDialog() {
        val builder: AlertDialog.Builder = if (getThemResId() != -1)
            AlertDialog.Builder(this, getThemResId()) else AlertDialog.Builder(this)

        builder.setCancelable(isCancelable)
        builder.setView(if (getLayoutIdLoading() == -1) R.layout.layout_loading_dialog_default else getLayoutIdLoading())
        loading = builder.create()
    }

    /**
     * Init dialog loading 2
     */
    private fun initDialog2() {
        val builder: AlertDialog.Builder = if (getThemResId() != -1)
            AlertDialog.Builder(this, getThemResId()) else AlertDialog.Builder(this)

        builder.setCancelable(isCancelable2)
        builder.setView(if (getLayoutIdLoading() == -1) R.layout.layout_loading_dialog_default else getLayoutIdLoading())
        loading2 = builder.create()
    }

    /**
     * Show dialog loading
     */
    open fun showDialog() {
        if (!loading.isShowing) {
            loading.show()
        }
    }

    /**
     * Show dialog loading 2
     */
    open fun showDialog2() {
        if (!loading2.isShowing) {
            loading2.show()
        }
    }

    /**
     * Hide dialog loading 2
     */
    open fun hideDialog2() {
        if (loading2.isShowing) {
            loading2.dismiss()
        }
    }

    /**
     * Hide dialog loading
     */
    open fun hideDialog() {
        if (loading.isShowing) {
            loading.dismiss()
        }
    }

    /**
     * Set cancelable dialog
     */
    fun setCancelableDialog(isCancelable: Boolean) {
        this.isCancelable = isCancelable
    }

    /**
     * Set cancelable dialog 2
     */
    fun setCancelableDialog2(isCancelable: Boolean) {
        this.isCancelable2 = isCancelable
    }
}