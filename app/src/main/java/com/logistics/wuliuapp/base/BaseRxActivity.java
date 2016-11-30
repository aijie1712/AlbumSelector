package com.logistics.wuliuapp.base;

import android.os.Bundle;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016-11-25
 *
 * @desc 有网络请求的页面
 */

public abstract class BaseRxActivity<T extends BaseContract.BasePresenter> extends BaseActivity {
    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachView();
        initData();
    }

    /**
     * [此方法不可再重写]
     */
    public final void attachView() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
