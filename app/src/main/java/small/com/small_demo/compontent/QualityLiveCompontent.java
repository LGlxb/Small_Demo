package small.com.small_demo.compontent;

import dagger.Component;
import small.com.small_demo.di.presenter.QualityLivePresenter;
import small.com.small_demo.module.QualityLiveModule;

@Component(modules = QualityLiveModule.class)//提供
public abstract class QualityLiveCompontent {
    public abstract void QualityLiveInject(QualityLivePresenter qualityLivePresenter);//使用
}

