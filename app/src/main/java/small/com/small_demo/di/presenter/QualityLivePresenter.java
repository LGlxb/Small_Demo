package small.com.small_demo.di.presenter;

import javax.inject.Inject;

import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.compontent.DaggerFashionCompontent;
import small.com.small_demo.compontent.DaggerQualityLiveCompontent;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.model.FashionModel;
import small.com.small_demo.di.model.QualityLiveModel;
import small.com.small_demo.module.FashionModule;
import small.com.small_demo.module.QualityLiveModule;

public class QualityLivePresenter extends BasePresenter<DataCall> {

    @Inject
    QualityLiveModel qualityLiveModel;

    public QualityLivePresenter(DataCall view) {
        super(view);
    }


    @Override
    protected void initModel() {
        //耦合性处理
        DaggerQualityLiveCompontent.builder().qualityLiveModule(new QualityLiveModule()).build()
                .QualityLiveInject(this);

    }

    public void qualityLive() {
        qualityLiveModel.qualityLive(new QualityLiveModel.QualityLiveModelCallBack() {
            @Override
            public void getSuccess(HomeGoodsBean homeGoodsBean) {

                view.onQualityLive(homeGoodsBean);
            }

            @Override
            public void getFaid(String error) {
                view.onFaild(error);
            }
        });
    }
}