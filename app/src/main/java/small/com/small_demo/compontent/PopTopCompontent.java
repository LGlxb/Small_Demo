package small.com.small_demo.compontent;



import dagger.Component;
import small.com.small_demo.di.presenter.PopTopPresenter;
import small.com.small_demo.module.PopTopModule;

@Component(modules = PopTopModule.class)//提供
public abstract class PopTopCompontent {
    public abstract void PopTopInject(PopTopPresenter popTopPresenter);//使用
}

