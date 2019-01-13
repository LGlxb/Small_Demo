package small.com.small_demo.compontent;
import dagger.Component;
import small.com.small_demo.di.presenter.RegisterPresenter;
import small.com.small_demo.module.RegisterModule;

@Component(modules = RegisterModule.class)//提供
public abstract class RegisterCompontent {
    public abstract void RegisterInject(RegisterPresenter registerPresenter);//使用
}

