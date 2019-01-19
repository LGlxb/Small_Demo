package small.com.small_demo.removecoupling.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.LoginModel;

@Module
public class LoginModule {
    @Provides
    public LoginModel getLoginModel() {
        //提供对象
        return new LoginModel();
    }
}