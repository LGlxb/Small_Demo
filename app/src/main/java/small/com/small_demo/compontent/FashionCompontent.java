package small.com.small_demo.compontent;

import dagger.Component;
import small.com.small_demo.di.model.FashionModel;
import small.com.small_demo.di.presenter.FashionPresenter;
import small.com.small_demo.di.presenter.HotGoodsPresenter;
import small.com.small_demo.module.FashionModule;
import small.com.small_demo.module.HotGoodsModule;

@Component(modules = FashionModule.class)//提供
public abstract class FashionCompontent {
    public abstract void FashionInject(FashionPresenter fashionPresenter);//使用
}

