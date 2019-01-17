package small.com.small_demo.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.PopTopModel;

@Module
public class PopTopModule {
    @Provides
    public PopTopModel  popTopModel() {
        //提供对象
        return new PopTopModel();
    }
}