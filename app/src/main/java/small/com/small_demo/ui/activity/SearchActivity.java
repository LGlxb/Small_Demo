package small.com.small_demo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import small.com.small_demo.R;
import small.com.small_demo.custom.FlowView;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.s1)
    ImageView s1;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.flowview1)
    FlowView flowview1;
    @BindView(R.id.flowview)
    FlowView flowview;
    @BindView(R.id.btn_clear)
    Button btnClear;
    private List<String> stringList;
    private FrameLayout.LayoutParams params;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        ButterKnife.bind(this);
        //创建搜索数据集合
        stringList = new ArrayList<>();
        params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams
                .WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    @OnClick({R.id.search_text, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_text:
                String mSearch = searchEdit.getText().toString();
                stringList.add(mSearch);
                textView = new TextView(SearchActivity.this);
                textView.setText(mSearch);
                textView.setTextSize(25);
                textView.setPadding(25, 15, 25, 15);
                flowview.addView(textView, params);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SearchActivity.this, textView.getText().toString(), Toast
                                .LENGTH_SHORT).show();
                        textView.setClickable(true);
                    }
                });
                Intent intent = new Intent(SearchActivity.this, SearchGoodsActivity.class);
                intent.putExtra("keyWords", mSearch);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_clear:
                flowview.removeAllViews();
                break;
        }
    }
}
