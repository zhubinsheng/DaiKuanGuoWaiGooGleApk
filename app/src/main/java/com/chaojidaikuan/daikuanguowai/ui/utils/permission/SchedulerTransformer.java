package com.chaojidaikuan.daikuanguowai.ui.utils.permission;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerTransformer<T> implements ObservableTransformer<T, T> {

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread());
    }
}
