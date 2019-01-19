package small.com.small_demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import small.com.small_demo.R;
import small.com.small_demo.adapters.SearchGoodsAdapter;
import small.com.small_demo.bean.SearchGoodsBean;
import small.com.small_demo.di.core.GoodsDataCall;
import small.com.small_demo.di.presenter.SearchGoodsPresenter;

public class SearchGoodsActivity extends AppCompatActivity implements GoodsDataCall {

    @BindView(R.id.s1)
    ImageButton s1;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.search_goods_recy)
    RecyclerView searchGoodsRecy;
    @BindView(R.id.smart_refrech)
    SmartRefreshLayout smartRefrech;
    @BindView(R.id.search_b)
    LinearLayout searchB;
    private SearchGoodsPresenter searchGoodsPresenter;
    private int page = 1;
    private String keyWords;
    private SearchGoodsAdapter searchGoodsAdapter;
    private String dataName;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        ButterKnife.bind(this);
        searchGoodsPresenter = new SearchGoodsPresenter(this);
        Intent intent = getIntent();
        keyWords = intent.getStringExtra("keyWords");
        dataName = intent.getStringExtra("dataName");
        Log.d("SearchGoodsActivity", keyWords + "++++++keywords");
        Log.d("SearchGoodsActivity", dataName + "+++++++dataname");
        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyWords = searchEdit.getText().toString();
                searchGoodsPresenter.searchGoods(keyWords, 1, 10);
            }
        });
        if (dataName != null) {
            searchGoodsPresenter.searchGoods(dataName, page, 10);

        } else {
            searchGoodsPresenter.searchGoods(keyWords, page, 10);

        }
        //刷新加载
        smartRefrech.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                //更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        searchGoodsPresenter.searchGoods(keyWords, page, 10);
                        Toast.makeText(SearchGoodsActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
        smartRefrech.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                //更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        searchGoodsPresenter.searchGoods(keyWords, page, 10);
                        Toast.makeText(SearchGoodsActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void onShowSearchGoods(SearchGoodsBean searchGoodsBean) {
        List<SearchGoodsBean.ResultBean> result = searchGoodsBean.getResult();
        if (searchGoodsBean.getStatus().equals("0000")) {
            //布局管理
            GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchGoodsActivity
                    .this, 2);
            searchGoodsRecy.setLayoutManager(gridLayoutManager);
            //设置适配器
            searchGoodsAdapter = new SearchGoodsAdapter(R.layout
                    .goods_search_key_word_item, result);
             if (searchGoodsBean.getResult().size() != 0) {
                searchGoodsRecy.setAdapter(searchGoodsAdapter);
                searchB.setVisibility(View.GONE);
                searchGoodsRecy.setVisibility(View.VISIBLE);
                searchGoodsAdapter.notifyDataSetChanged();

            } else {
                searchB.setVisibility(View.VISIBLE);
                searchGoodsRecy.setVisibility(View.GONE);
                searchGoodsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onShowFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context CONTEXT() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
