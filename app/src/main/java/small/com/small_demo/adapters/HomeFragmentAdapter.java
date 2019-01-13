package small.com.small_demo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    public HomeFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int postion) {
        return fragmentList.get(postion);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
