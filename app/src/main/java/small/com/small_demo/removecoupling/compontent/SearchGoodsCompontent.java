package small.com.small_demo.removecoupling.compontent;

import dagger.Component;
import small.com.small_demo.di.presenter.SearchGoodsPresenter;
import small.com.small_demo.removecoupling.module.SearchGoodsModule;

@Component(modules = SearchGoodsModule.class)//提供
public abstract class SearchGoodsCompontent {
    public abstract void SearchGoodsInject(SearchGoodsPresenter searchGoodsPresenter);//使用
}

