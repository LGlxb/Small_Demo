package small.com.small_demo.removecoupling.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.PopButtomModel;
import small.com.small_demo.di.model.PopTopModel;

@Module
public class PopButtomModule {
    @Provides
    public PopButtomModel popButtomModel() {
        //提供对象
        return new PopButtomModel();
    }
}