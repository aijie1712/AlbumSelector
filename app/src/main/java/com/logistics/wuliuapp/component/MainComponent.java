package com.logistics.wuliuapp.component;

import com.logistics.wuliuapp.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016-11-28
 *
 * @desc
 */
@Component(dependencies = AppComponent.class)
public interface MainComponent {
    MainActivity inject(MainActivity mainActivity);
}
