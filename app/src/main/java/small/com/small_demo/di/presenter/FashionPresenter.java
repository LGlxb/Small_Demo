package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import dagger.Module;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.compontent.DaggerFashionCompontent;
import small.com.small_demo.compontent.DaggerHotGoodsCompontent;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.model.FashionModel;
import small.com.small_demo.di.model.HotGoodsModel;
import small.com.small_demo.module.FashionModule;
import small.com.small_demo.module.HotGoodsModule;

public class FashionPresenter extends BasePresenter<DataCall> {

    @Inject
    FashionModel fashionModel;

    public FashionPresenter(DataCall view) {
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