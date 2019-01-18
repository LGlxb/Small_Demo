package small.com.small_demo.removecoupling.compontent;



import dagger.Component;
import small.com.small_demo.di.presenter.LoginPresenter;
import small.com.small_demo.removecoupling.module.LoginModule;

@Component(modules = LoginModule.class)//提供
public abstract class LoginCompontent {
    public abstract void loginInject(LoginPresenter loginPresenter);//使用
}

