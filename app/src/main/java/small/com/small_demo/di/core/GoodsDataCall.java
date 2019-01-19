package small.com.small_demo.di.core;

import small.com.small_demo.bean.SearchGoodsBean;

public interface GoodsDataCall extends IView {
    void onShowSearchGoods(SearchGoodsBean searchGoodsBean);

    void onShowFailure(String error);
}
