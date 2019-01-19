package small.com.small_demo.removecoupling.module;


import dagger.Module;
import dagger.Provides;
import small.com.small_demo.di.model.SearchGoodsModel;

@Module
public class SearchGoodsModule {
    @Provides
    public SearchGoodsModel searchGoodsModel() {
        //提供对象
        return new SearchGoodsModel();
    }
}