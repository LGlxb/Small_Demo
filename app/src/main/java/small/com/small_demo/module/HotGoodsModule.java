package small.com.small_demo.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.HotGoodsModel;

@Module
public class HotGoodsModule {
    @Provides
    public HotGoodsModel hotGoodsModel() {
        //提供对象
        return new HotGoodsModel();
    }
}