package small.com.small_demo.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.LoginModel;
import small.com.small_demo.di.model.RegisterModel;

@Module
public class RegisterModule {
    @Provides
    public RegisterModel getRegisterModel() {
        //提供对象
        return new RegisterModel();
    }
}