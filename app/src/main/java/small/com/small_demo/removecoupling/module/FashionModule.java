package small.com.small_demo.removecoupling.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.FashionModel;
import small.com.small_demo.di.model.HotGoodsModel;

@Module
public class FashionModule {
    @Provides
    public FashionModel fashionModel() {
        //提供对象
        return new FashionModel();
    }
}