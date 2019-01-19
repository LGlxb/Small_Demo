package small.com.small_demo.removecoupling.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.QualityLiveModel;

@Module
public class QualityLiveModule {
    @Provides
    public QualityLiveModel qualityLiveModel() {
        //提供对象
        return new QualityLiveModel();
    }
}