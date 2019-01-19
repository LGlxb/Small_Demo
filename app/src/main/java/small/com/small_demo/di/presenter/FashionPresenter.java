package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.FashionModel;
import small.com.small_demo.removecoupling.compontent.DaggerFashionCompontent;
import small.com.small_demo.removecoupling.module.FashionModule;

public class FashionPresenter extends BasePresenter<HomeDataCall> {

    @Inject
    FashionModel fashionModel;

    public FashionPresenter(HomeDataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerFashionCompontent.builder().fashionModule(new FashionModule()).build()
                .FashionInject(this);

    }

    public void hotGoods() {
        fashionModel.fashion(new FashionModel.FashionModelCallBack() {
            @Override
            public void getSuccess(HomeGoodsBean homeGoodsBean) {
                view.onFashion(homeGoodsBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}