package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.SearchGoodsBean;
import small.com.small_demo.di.core.GoodsDataCall;
import small.com.small_demo.di.core.HomeDataCall;
import small.com.small_demo.di.model.SearchGoodsModel;
import small.com.small_demo.removecoupling.compontent.DaggerSearchGoodsCompontent;
import small.com.small_demo.removecoupling.module.SearchGoodsModule;

public class SearchGoodsPresenter extends BasePresenter<GoodsDataCall> {

    @Inject
    SearchGoodsModel searchGoodsModel;

    public SearchGoodsPresenter(GoodsDataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerSearchGoodsCompontent.builder().searchGoodsModule(new SearchGoodsModule()).build()
                .SearchGoodsInject(this);
    }

    public void searchGoods(String keyWords, int page, int count) {
        searchGoodsModel.searchGoods(keyWords, page, count, new SearchGoodsModel
                .SearchGoodsModelCallBack() {

            @Override
            public void getSuccess(SearchGoodsBean searchGoodsBean) {
                view.onShowSearchGoods(searchGoodsBean);
            }

            @Override
            public void getFaid(String error) {
                view.onShowFailure(error);
            }
        });
    }
}