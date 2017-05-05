package drawthink.com.inventory.functions.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import drawthink.com.inventory.data.remote.server.inventory.vo.statistics.Statistic;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.cash.SellerItem;
import drawthink.com.inventory.functions.statistics.adapter.itemStatistic;
import drawthink.com.inventory.functions.statistics.contract.StaticContract;
import drawthink.com.inventory.functions.statistics.presenter.StatisticPresenter;
import kale.adapter.CommonAdapter;
import kale.adapter.item.AdapterItem;

public class StatisticsActivity extends BaseActivity implements StaticContract.View {

    @BindView(R.id.startTimeTv)
    TextView startTimeTv;
    @BindView(R.id.endTimeTv)
    TextView endTimeTv;
    @BindView(R.id.sellerSp)
    Spinner sellerSp;
    @BindView(R.id.productEt)
    EditText productEt;
    @BindView(R.id.queryBtn)
    ImageView queryBtn;
    @BindView(R.id.statisticsView)
    ListView statisticsView;
    @BindView(R.id.numCountTv)
    TextView numCountTv;
    @BindView(R.id.sellCountTv)
    TextView sellCountTv;
    @BindView(R.id.statisticsLayout)
    LinearLayout statisticsLayout;
    @BindView(R.id.payTypeSp)
    Spinner payTypeSp;
    private StaticContract.Presenter presenter;
    private CommonAdapter<Statistic.StatisticsBean> adapter;
    private CommonAdapter<ShopSeller> sellAdapter;

    private String storeId;
    private String startDate;
    private String endDate;
    private String productCode;
    private String productName;
    private int shopSellerId = 0;
    private String payMode;

    @BindArray(R.array.payType)
    String[] payTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        initListView();
        initDate();

        presenter.loadStaticInfo(InventoryApplication.getShopId() + "", "", "", shopSellerId, "", "");
        presenter.loadSaller(InventoryApplication.getShopId());
        payTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    payMode = "";
                }else{
                    payMode = payTypes[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initDate() {
        sellAdapter = new CommonAdapter<ShopSeller>(null, 1) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new SellerItem();
            }
        };
        sellerSp.setAdapter(sellAdapter);

        sellerSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ShopSeller shopSeller = sellAdapter.getData().get(i);
                shopSellerId = shopSeller.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    private void initListView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_statistics,
                statisticsView, false);
        TextView sellDate = (TextView) headerView.findViewById(R.id.sellDate);
        TextView sellName = (TextView) headerView.findViewById(R.id.sellName);
        TextView sellId = (TextView) headerView.findViewById(R.id.sellId);
        TextView price = (TextView) headerView.findViewById(R.id.price);
        TextView sellNum = (TextView) headerView.findViewById(R.id.sellNum);
        TextView sellColor = (TextView) headerView.findViewById(R.id.sellColor);
        TextView sellXi = (TextView) headerView.findViewById(R.id.sellXi);
        TextView sellSeller = (TextView) headerView.findViewById(R.id.sellSeller);
        TextView sellSum = (TextView) headerView.findViewById(R.id.sellSum);
        headerView.setBackgroundColor(Color.parseColor("#f6f8f7"));

        sellDate.setText("销售日期");
        sellDate.setTextColor(Color.parseColor("#333333"));

        sellName.setText("商品名称");
        sellName.setTextColor(Color.parseColor("#333333"));

        sellId.setText("支付类型");
        sellId.setTextColor(Color.parseColor("#333333"));

        price.setText("单价");
        price.setTextColor(Color.parseColor("#333333"));

        sellNum.setText("数量");
        sellNum.setTextColor(Color.parseColor("#333333"));

        sellColor.setText("颜色");
        sellColor.setTextColor(Color.parseColor("#333333"));

        sellXi.setText("型号");
        sellXi.setTextColor(Color.parseColor("#333333"));

        sellSeller.setText("销售员");
        sellSeller.setTextColor(Color.parseColor("#333333"));

        sellSum.setText("销售总额");
        sellSum.setTextColor(Color.parseColor("#333333"));

        statisticsView.addHeaderView(headerView);
        adapter = new CommonAdapter<Statistic.StatisticsBean>(null, 1) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new itemStatistic();
            }
        };
        statisticsView.setAdapter(adapter);

    }


    @Override
    public void showStaticInfo(Statistic statistic) {
        adapter.setData(statistic.getStatistics());
        numCountTv.setText(String.format("%s", statistic.getCount()));
        sellCountTv.setText(new DecimalFormat("¥#.##").format(statistic.getTotal()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSaler(List<ShopSeller> ShopSellers) {
        sellAdapter.setData(ShopSellers);
        sellAdapter.notifyDataSetChanged();
    }


    @Override
    public String setTitle() {
        return "统计";
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new StatisticPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @OnClick({R.id.startTimeTv, R.id.endTimeTv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startTimeTv:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            startTimeTv.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");
                break;
            case R.id.endTimeTv:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            endTimeTv.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");
                break;
        }
    }

    @OnClick(R.id.queryBtn)
    public void onClick() {
        if (startTimeTv.getText().toString().contains("请选择")) {
            startDate = "";
        }
        if (endTimeTv.getText().toString().contains("请选择")) {
            endDate = "";
        }
        presenter.loadStaticInfo(InventoryApplication.getShopId() + "", startDate,
                endDate, shopSellerId,
                payMode,
                productEt.getText().toString());
        adapter.notifyDataSetChanged();

    }
}
