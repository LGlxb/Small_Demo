package small.com.small_demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import small.com.small_demo.R;
import small.com.small_demo.adapters.HomeFragmentAdapter;
import small.com.small_demo.ui.fragments.Fragment_bill;
import small.com.small_demo.ui.fragments.Fragment_car;
import small.com.small_demo.ui.fragments.Fragment_home;
import small.com.small_demo.ui.fragments.Fragment_mine;
import small.com.small_demo.ui.fragments.Fragment_ufo;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.home_fragment_pager)
    ViewPager homeFragmentPager;
    @BindView(R.id.rb_home_page)
    RadioButton rbHomePage;
    @BindView(R.id.rb_home_ufo)
    RadioButton rbHomeUfo;
    @BindView(R.id.rb_home_cart)
    RadioButton rbHomeCart;
    @BindView(R.id.rb_home_bill)
    RadioButton rbHomeBill;
    @BindView(R.id.rb_home_mine)
    RadioButton rbHomeMine;
    @BindView(R.id.rg_home)
    RadioGroup rgHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        //创建fragment集合
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_home());
        fragmentList.add(new Fragment_ufo());
        fragmentList.add(new Fragment_car());
        fragmentList.add(new Fragment_bill());
        fragmentList.add(new Fragment_mine());

        //设置适配器
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager(), fragmentList);
        homeFragmentPager.setAdapter(homeFragmentAdapter);
        //滑动切换
        homeFragmentPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                rgHome.check(rgHome.getChildAt(i).getId());
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });



    }

    //点击切换
    @OnClick({R.id.rb_home_page, R.id.rb_home_ufo, R.id.rb_home_cart, R
            .id.rb_home_bill, R.id.rb_home_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home_page:
                homeFragmentPager.setCurrentItem(0, false);
                break;
            case R.id.rb_home_ufo:
                homeFragmentPager.setCurrentItem(1, false);
                break;
            case R.id.rb_home_cart:
                homeFragmentPager.setCurrentItem(2, false);
                break;
            case R.id.rb_home_bill:
                homeFragmentPager.setCurrentItem(3, false);
                break;
            case R.id.rb_home_mine:
                homeFragmentPager.setCurrentItem(4, false);
                break;
        }
    }
}
