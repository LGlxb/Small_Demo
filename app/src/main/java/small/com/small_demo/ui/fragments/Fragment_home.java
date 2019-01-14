package small.com.small_demo.ui.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import small.com.small_demo.R;
import small.com.small_demo.adapters.FshionAdapter;
import small.com.small_demo.adapters.HotGoodsAdapter;
import small.com.small_demo.adapters.QualityLiveAdapter;
import small.com.small_demo.bean.HomeBannerBean;
import small.com.small_demo.bean.HomeGoodsBean;
import small.com.small_demo.bean.LoginBean;
import small.com.small_demo.bean.PopButtomBean;
import small.com.small_demo.bean.PopTopBean;
import small.com.small_demo.bean.RegisterBean;
import small.com.small_demo.di.core.DataCall;
import small.com.small_demo.di.presenter.FashionPresenter;
import small.com.small_demo.di.presenter.HomeBannerPresenter;
import small.com.small_demo.di.presenter.HotGoodsPresenter;
import small.com.small_demo.di.presenter.PopButtomPresenter;
import small.com.small_demo.di.presenter.PopTopPresenter;
import small.com.small_demo.di.presenter.QualityLivePresenter;

public class Fragment_home extends Fragment implements DataCall {

    @BindView(R.id.home_img_menu)
    ImageButton homeImgMenu;

    @BindView(R.id.home_img_search)
    ImageButton homeImgSearch;
    @BindView(R.id.home_page_banner)
    MZBannerView homePageBanner;
    @BindView(R.id.home_img_more_yellow)
    ImageView homeImgMoreYellow;
    @BindView(R.id.home_hot_sale_goods)
    RecyclerView homeHotSaleGoods;
    @BindView(R.id.home_img_more_purple)
    ImageView homeImgMorePurple;
    @BindView(R.id.home_page_magic_goods)
    RecyclerView homePageMagicGoods;
    @BindView(R.id.home_img_more_pink)
    ImageView homeImgMorePink;
    @BindView(R.id.home_q_life_goods)
    RecyclerView homeQLifeGoods;
    @BindView(R.id.home_edit_search)
    android.widget.EditText homeEditSearch;
    private View homeView;
    private HomeBannerPresenter homeBannerPresenter;
    private Unbinder unbinder;
    private HotGoodsPresenter hotGoodsPresenter;
    private FashionPresenter fashionPresenter;
    private QualityLivePresenter qualityLivePresenter;
    private PopTopPresenter popTopPresenter;
    private PopButtomPresenter popButtomPresenter;
    private String firstCategoryId = "1001002";
    private View popview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, homeView);
        popview = View.inflate(getActivity(), R.layout.item_pop, null);
        //实例化P层
        homeBannerPresenter = new HomeBannerPresenter(this);
        homeBannerPresenter.homeBanner();
        hotGoodsPresenter = new HotGoodsPresenter(this);
        hotGoodsPresenter.hotGoods();
        fashionPresenter = new FashionPresenter(this);
        fashionPresenter.hotGoods();
        qualityLivePresenter = new QualityLivePresenter(this);
        qualityLivePresenter.qualityLive();
        popTopPresenter = new PopTopPresenter(this);
        popTopPresenter.popTop();
        popButtomPresenter = new PopButtomPresenter(this);
        popButtomPresenter.popTop(firstCategoryId);
        return homeView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homePageBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(getContext(), "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ButterKnife.bind(this, homeView);
    }

    @OnClick({R.id.home_img_menu, R.id.home_img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_img_menu:
                PopupWindow popupWindow = new PopupWindow(popview, 800, 200,
                        true);
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
                popupWindow.showAsDropDown(homeImgMenu);
                //TopRecy
                RecyclerView topRecy = popview.findViewById(R.id.home_top_recycler);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                topRecy.setLayoutManager(linearLayoutManager);
                topRecy.setBackgroundColor(0x88000000);

                RecyclerView bottonRecycler = popview.findViewById(R.id.home_botton_recycler);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
                linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                bottonRecycler.setLayoutManager(linearLayoutManager1);
                bottonRecycler.setBackgroundColor(0x77000000);
                break;
            case R.id.home_img_search:
                break;
        }
    }

    //轮播图
    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.Home_BannerImg);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            Glide.with(context).load(data).into(mImageView);
        }
    }

    @Override
    public void onLogin(LoginBean loginBean) {

    }

    @Override
    public void onPopRecyOne(PopTopBean popTopBean) {
//        List<PopTopBean.ResultBean> topResult = popTopBean.getResult(); //布局管理
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
//        homeQLifeGoods.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onPopRecyTwo(PopButtomBean popButtomBean) {

    }

    @Override
    public void onHotGoods(HomeGoodsBean homeGoodsBean) {
        List<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean> rxxp = homeGoodsBean.getResult
                ().getRxxp().get(0).getCommodityList();
        //瀑布流布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        homeHotSaleGoods.setLayoutManager(staggeredGridLayoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //设置适配器
        HotGoodsAdapter hotGoodsAdapter = new HotGoodsAdapter(R.layout.goods_rxxp_item, rxxp);
        homeHotSaleGoods.setAdapter(hotGoodsAdapter);

    }

    @Override
    public void onFashion(HomeGoodsBean homeGoodsBean) {
        List<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> pzList = homeGoodsBean.getResult
                ().getMlss().get(0).getCommodityList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        homePageMagicGoods.setLayoutManager(linearLayoutManager);
        //设置适配器
        FshionAdapter hotGoodsAdapter = new FshionAdapter(R.layout.goods_mlss_item, pzList);
        homePageMagicGoods.setAdapter(hotGoodsAdapter);
    }

    @Override
    public void onQualityLive(HomeGoodsBean homeGoodsBean) {
        List<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = homeGoodsBean
                .getResult().getPzsh().get(0).getCommodityList();
        //布局管理
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        homeQLifeGoods.setLayoutManager(gridLayoutManager);
        //设置适配器
        QualityLiveAdapter qualityLiveAdapter = new QualityLiveAdapter(R.layout.goods_pzsh_item,
                commodityList);
        homeQLifeGoods.setAdapter(qualityLiveAdapter);
    }

    @Override
    public void onRegister(RegisterBean registerBean) {

    }

    @Override
    public void onBanner(HomeBannerBean homeBannerBean) {
        homePageBanner.start();//开始轮播
        if (homeBannerBean.getStatus().equals("0000")) {
            List<HomeBannerBean.ResultBean> bannerBeanResult = homeBannerBean.getResult();
            List<String> ImgUrl = new ArrayList<>();
            for (int i = 0; i < bannerBeanResult.size(); i++) {
                ImgUrl.add(bannerBeanResult.get(i).getImageUrl());
            }
            //设置数据
            homePageBanner.setPages(ImgUrl, new MZHolderCreator() {
                @Override
                public MZViewHolder createViewHolder() {
                    return new BannerViewHolder();
                }
            });
        }
    }


    @Override
    public void onFaild(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context CONTEXT() {
        return getActivity();
    }
}
