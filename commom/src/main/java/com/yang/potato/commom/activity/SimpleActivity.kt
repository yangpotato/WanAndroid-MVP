package com.yang.potato.commom.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViewStatus()
        initToolbar()
        initData()
        initView()
    }

    /**
     * 设置布局
     */
    protected abstract fun getLayoutId() : Int

    /**
     * 初始化界面状态
     */
    protected abstract fun initViewStatus()

    /**
     * 初始化ToolBar
     */
    protected abstract fun initToolbar()

    /**
     * 初始化布局
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 重新加载数据
     */
    protected abstract fun reload()
}