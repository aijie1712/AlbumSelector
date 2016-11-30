package com.logistics.wuliuapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.logistics.wuliuapp.CustomApplication;
import com.logistics.wuliuapp.R;
import com.logistics.wuliuapp.component.AppComponent;
import com.logistics.wuliuapp.widget.loadding.CustomDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private CustomDialog dialog;//进度条

    Toolbar commonToolbar;

    protected TextView title_center, title_right;
    protected ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        setupActivityComponent(CustomApplication.getInstance().getAppComponent());
        commonToolbar = ButterKnife.findById(this, R.id.toolbar);
        if (commonToolbar != null) {
            setSupportActionBar(commonToolbar);
            title_center = ButterKnife.findById(this, R.id.title_center);
            title_right = ButterKnife.findById(this, R.id.title_right);
            iv_back = ButterKnife.findById(this, R.id.iv_back);
        }
        initView();
        initData();
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 设置title
     *
     * @param title
     */
    protected void setTitleCenter(String title) {
        title_center.setText(title);
    }

    /**
     * @param titleRight
     */
    protected void setTitleRight(String titleRight, View.OnClickListener onClickListener) {
        visible(title_right);
        title_right.setText(titleRight);
        title_right.setOnClickListener(onClickListener);
    }

    /**
     * 显示返回箭头，点击返回
     */
    protected void showBack() {
        visible(iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        dismissDialog();
    }

    protected abstract int getLayoutId();

    protected void initView(){}

    protected void initData() {
    }

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    // dialog
    public CustomDialog getDialog() {
        if (dialog == null) {
            dialog = CustomDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    public void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    public void showDialog() {
        getDialog().show();
    }

    public void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
