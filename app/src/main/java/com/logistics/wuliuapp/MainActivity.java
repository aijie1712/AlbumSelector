package com.logistics.wuliuapp;

import android.util.Log;

import com.logistics.wuliuapp.api.Api;
import com.logistics.wuliuapp.base.BaseActivity;
import com.logistics.wuliuapp.component.AppComponent;
import com.logistics.wuliuapp.component.DaggerMainComponent;
import com.logistics.wuliuapp.model.BaseModel;
import com.logistics.wuliuapp.utils.LogUtils;
import com.logistics.wuliuapp.widget.ScrollControlViewPager;
import com.logistics.wuliuapp.widget.tabview.TabItem;

import javax.inject.Inject;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Bind(R.id.viewPager)
    ScrollControlViewPager viewPager;
    @Bind(R.id.actionbar_info)
    TabItem actionbarInfo;
    @Bind(R.id.actionbar_publish)
    TabItem actionbarPublish;
    @Bind(R.id.actionbar_attention)
    TabItem actionbarAttention;
    @Bind(R.id.actionbar_mine)
    TabItem actionbarMine;

    @Inject
    Api api;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        api.getTest("", 1, 10, "", "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseModel>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.i("onCompleted==");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LogUtils.e("onError==" + e.toString());
                        Log.e("aijie", "onError==", e);
                    }

                    @Override
                    public void onNext(BaseModel baseModel) {
                        LogUtils.i("onNext==" + baseModel.toString());
                    }
                });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder().appComponent(appComponent).build().inject(this);
    }
}
