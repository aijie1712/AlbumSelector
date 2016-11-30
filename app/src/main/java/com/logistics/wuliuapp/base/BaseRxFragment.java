package com.logistics.wuliuapp.base;

import com.logistics.wuliuapp.component.AppComponent;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016-11-25
 *
 * @desc 有网络请求的页面
 */

public abstract class BaseRxFragment<T extends BaseContract.BasePresenter> extends BaseFragment {
    @Inject
    T mPresenter;

    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * [此方法不可再重写]
     */
    @Override
    public final void attachView() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
