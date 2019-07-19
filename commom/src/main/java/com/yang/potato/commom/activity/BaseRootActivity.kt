package com.yang.potato.commom.activity

import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yang.potato.commom.BaseConstracts
import com.yang.potato.commom.R

abstract class BaseRootActivity : SimpleActivity() {
    /**
     * 正常布局
     */
    protected lateinit var mNormalView : View
    /**
     * 加载中布局
     */
    protected lateinit var mLoadingView : View
    /**
     * 加载失败布局
     */
    protected lateinit var mErrorView : View
    /**
     * Toolbar
     */
    protected lateinit var mToolbar : Toolbar
    /**
     * 当前显示状态
     */
    protected var mStatus = BaseConstracts.STATUS_LOADING

    /**
     * 初始化布局状态
     */
    override fun initViewStatus() {
        try {
            mToolbar = findViewById(R.id.toolbar)
            initToolbarView()
        } catch (e : RuntimeException){
            Log.v("BaseRootActivity", "not find toolbar")
        }
        try {
            mNormalView = findViewById(R.id.normal)
        } catch (e : RuntimeException){
            Log.v("BaseRootActivity", "not find normal")
            return
        }
        if(mNormalView.parent !is ViewGroup){
            Log.v("BaseRootActivity", "Normal's parent need ViewGroup")
            return
        }
        mNormalView.visibility = View.GONE
        val parent : ViewGroup = mNormalView.parent as ViewGroup
        View.inflate(this, R.layout.layout_error, parent)
        View.inflate(this, R.layout.layout_loading, parent)
        mErrorView = findViewById(R.id.error_view)
        mLoadingView = findViewById(R.id.loading_view)
        mErrorView.findViewById<TextView>(R.id.tv_reload)?.setOnClickListener { reload() }
        mErrorView.visibility = View.GONE
        mLoadingView.visibility = View.VISIBLE
    }

    private fun initToolbarView() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        mToolbar.setNavigationOnClickListener { finish() }
    }

    /**
     * 显示正常布局
     */
    protected fun showNormal() {
        if(mStatus == BaseConstracts.STATUS_NORMAL)
            return
        hideView()
        mNormalView.visibility = View.INVISIBLE
        mStatus = BaseConstracts.STATUS_NORMAL
    }

    /**
     * 显示加载布局
     */
    protected fun showLoading() {
        if(mStatus == BaseConstracts.STATUS_LOADING)
            return
        hideView()
        mLoadingView.visibility = View.INVISIBLE
        mStatus = BaseConstracts.STATUS_LOADING
    }

    /**
     * 显示错误布局
     */
    protected fun showError() {
        if(mStatus == BaseConstracts.STATUS_ERROR)
            return
        hideView()
        mErrorView.visibility = View.INVISIBLE
        mStatus = BaseConstracts.STATUS_ERROR
    }

    /**
     * 显示隐藏View
     */
    private fun hideView() {
        when(mStatus){
            BaseConstracts.STATUS_NORMAL -> mNormalView.visibility = View.GONE
            BaseConstracts.STATUS_LOADING -> mLoadingView.visibility = View.GONE
            BaseConstracts.STATUS_ERROR -> mErrorView.visibility = View.GONE
        }
    }
}