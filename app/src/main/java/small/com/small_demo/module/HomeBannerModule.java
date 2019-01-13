package small.com.small_demo.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.HomeBannerModel;
import small.com.small_demo.di.model.LoginModel;

@Module
public class HomeBannerModule {
    @Provides
    public HomeBannerModel getHomeBannerModel() {
        //提供对象
        return new HomeBannerModel();
    }
}