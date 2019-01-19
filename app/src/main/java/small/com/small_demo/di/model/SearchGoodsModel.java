package small.com.small_demo.di.model;

import com.google.gson.Gson;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.SearchGoodsBean;
import small.com.small_demo.utils.OkHttpUtils;

public class SearchGoodsModel {
    public void searchGoods(String keyWords, int page, int count, final
    SearchGoodsModelCallBack searchGoodsModelCallBack) {

        String SearchGoods_Url = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword" +
                "?keyword=" + keyWords + "&page=" + page + "&count=" + count;
        OkHttpUtils okHttpUtils = OkHttpUtils.getOkHttpUtils();
        okHttpUtils.doGet(SearchGoods_Url, new OkHttpUtils.IOKHttpUtilsCallBack() {
            @Override
            public void onFailure(String error) {
                searchGoodsModelCallBack.getFaid(error);
            }

            @Override
            public void onResponse(String json) {
                SearchGoodsBean searchGoodsBean = new Gson().fromJson(json, SearchGoodsBean.class);
                if (searchGoodsBean.getStatus().equals("0000")) {
                    searchGoodsModelCallBack.getSuccess(searchGoodsBean);

                }
            }
        });
    }

    //接口回调
    public interface SearchGoodsModelCallBack {
        void getSuccess(SearchGoodsBean searchGoodsBean);

        void getFaid(String error);
    }
}
