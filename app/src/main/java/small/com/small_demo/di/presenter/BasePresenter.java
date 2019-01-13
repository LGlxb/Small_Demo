package small.com.small_demo.di.presenter;

import java.lang.ref.SoftReference;

import small.com.small_demo.di.core.IView;

public abstract class BasePresenter<V extends IView> {
    protected V view;
    private final SoftReference<V> vSoftReference;

    public BasePresenter(V view) {
        //软引用包裹
        vSoftReference = new SoftReference<>(view);
        this.view = view;
        initModel();
    }

    protected abstract void initModel();

    void onDestroy() {
        view = null;  //减少内存溢出时调用
    }
}