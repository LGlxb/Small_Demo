package small.com.small_demo.removecoupling.compontent;


import dagger.Component;
import small.com.small_demo.di.presenter.PopButtomPresenter;
import small.com.small_demo.removecoupling.module.PopButtomModule;

@Component(modules = PopButtomModule.class)//提供
public abstract class PopButtomCompontent {
    public abstract void PopButtomInject(PopButtomPresenter popButtomPresenter);//使用
}

