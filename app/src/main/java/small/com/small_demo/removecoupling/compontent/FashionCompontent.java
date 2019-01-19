package small.com.small_demo.removecoupling.compontent;

import dagger.Component;
import small.com.small_demo.di.presenter.FashionPresenter;
import small.com.small_demo.removecoupling.module.FashionModule;

@Component(modules = FashionModule.class)//提供
public abstract class FashionCompontent {
    public abstract void FashionInject(FashionPresenter fashionPresenter);//使用
}

