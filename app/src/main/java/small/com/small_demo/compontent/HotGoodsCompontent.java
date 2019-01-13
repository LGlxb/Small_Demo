package small.com.small_demo.compontent;

import dagger.Component;
import small.com.small_demo.di.presenter.HotGoodsPresenter;
import small.com.small_demo.module.HotGoodsModule;

@Component(modules = HotGoodsModule.class)//提供
public abstract class HotGoodsCompontent {
    public abstract void HotGoodsInject(HotGoodsPresenter hotGoodsPresenter);//使用
}

