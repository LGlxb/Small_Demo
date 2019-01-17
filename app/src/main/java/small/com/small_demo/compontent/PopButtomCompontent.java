package small.com.small_demo.compontent;


import dagger.Component;
import small.com.small_demo.di.presenter.PopButtomPresenter;
import small.com.small_demo.module.PopButtomModule;
import small.com.small_demo.module.PopTopModule;

@Component(modules = PopButtomModule.class)//提供
public abstract class PopButtomCompontent {
    public abstract void PopButtomInject(PopButtomPresenter popButtomPresenter);//使用
}

