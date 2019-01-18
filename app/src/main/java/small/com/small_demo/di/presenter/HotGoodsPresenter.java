package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.HotGoodsModel;
import small.com.small_demo.removecoupling.compontent.DaggerHotGoodsCompontent;
import small.com.small_demo.removecoupling.module.HotGoodsModule;

public class HotGoodsPresenter extends BasePresenter<HomeDataCall> {

    @Inject
    HotGoodsModel hotGoodsModel;

    public HotGoodsPresenter(HomeDataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
//        //耦合性处理
        DaggerHotGoodsCompontent.builder().hotGoodsModule(new HotGoodsModule()).build()
                .HotGoodsInject(this);

    }

    public void hotGoods() {
        hotGoodsModel.hotGoods(new HotGoodsModel.HotModelCallBack() {
            @Override
            public void getSuccess(HomeGoodsBean homeGoodsBean) {
                view.onHotGoods(homeGoodsBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}