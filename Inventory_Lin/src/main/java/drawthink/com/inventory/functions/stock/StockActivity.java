package drawthink.com.inventory.functions.stock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.datetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.remote.server.inventory.vo.stock.ShopStockInfo;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.stock.adapter.StockItem;
import drawthink.com.inventory.functions.stock.contract.StockContract;
import drawthink.com.inventory.functions.stock.presenter.StockPresenter;
import kale.adapter.CommonAdapter;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> StockActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 11:51<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class StockActivity extends BaseActivity implements StockContract.View {
    @BindView(R.id.et_startdatatime)
    TextView etStartdatatime;
    @BindView(R.id.et_enddatatime)
    TextView etEnddatatime;
    @BindView(R.id.et_stockName)
    EditText etStockName;

    @BindView(R.id.lv_stock)
    ListView lvStock;
    @BindView(R.id.ib_stock_search)
    ImageView ibStockSearch;
    private StockContract.Presenter presenter;
    private CommonAdapter<ShopStockInfo> adapter;
    private String startDate;
    private String endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock, true);
        ButterKnife.bind(this);
        //initDatePicker();
        initListView();
        presenter.loadStockInfo(InventoryApplication.getShopId(), "", "", "", "");
    }

    private void initListView() {
        View stockHeaderView = LayoutInflater.from(this).inflate(R.layout.item_stock, lvStock, false);
        TextView stockData = (TextView) stockHeaderView.findViewById(R.id.stock_data);
        TextView stockName = (TextView) stockHeaderView.findViewById(R.id.stock_name);
        TextView stockSellNum = (TextView) stockHeaderView.findViewById(R.id.stock_number);
        TextView stockCategory = (TextView) stockHeaderView.findViewById(R.id.stock_category);
        TextView stockState = (TextView) stockHeaderView.findViewById(R.id.stock_state);
        TextView stockColor = (TextView) stockHeaderView.findViewById(R.id.stock_color);
        TextView stockPrice = (TextView) stockHeaderView.findViewById(R.id.stock_price);
        TextView stockSum = (TextView) stockHeaderView.findViewById(R.id.stock_sum);
        TextView stockSaleVolume = (TextView) stockHeaderView.findViewById(R.id.stock_saleVolume);
        TextView stockCurrentNum = (TextView) stockHeaderView.findViewById(R.id.stock_currentNum);
        TextView stockSale = (TextView) stockHeaderView.findViewById(R.id.stock_sale);

        stockHeaderView.setBackgroundColor(Color.parseColor("#f6f8f7"));
        stockData.setText("日期");
        stockData.setTextColor(Color.parseColor("#333333"));

        stockName.setText("商品名称");
        stockName.setTextColor(Color.parseColor("#333333"));

        stockSellNum.setText("商品编号");
        stockSellNum.setTextColor(Color.parseColor("#333333"));

        stockCategory.setText("类别");
        stockCategory.setTextColor(Color.parseColor("#333333"));

        stockState.setText("型号");
        stockState.setTextColor(Color.parseColor("#333333"));

        stockColor.setText("颜色");
        stockColor.setTextColor(Color.parseColor("#333333"));

        stockPrice.setText("单价");
        stockPrice.setTextColor(Color.parseColor("#333333"));

        stockSum.setText("总库存");
        stockSum.setTextColor(Color.parseColor("#333333"));

        stockSaleVolume.setText("售出数量");
        stockSaleVolume.setTextColor(Color.parseColor("#333333"));

        stockCurrentNum.setText("当前库存");
        stockCurrentNum.setTextColor(Color.parseColor("#333333"));

        stockSale.setText("销售总额");
        stockSale.setTextColor(Color.parseColor("#333333"));

        lvStock.addHeaderView(stockHeaderView);
        adapter = new CommonAdapter<ShopStockInfo>(null, 1) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new StockItem();
            }
        };
        lvStock.setAdapter(adapter);

    }

    @OnClick({R.id.et_startdatatime, R.id.et_enddatatime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_startdatatime:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            etStartdatatime.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");
                break;
            case R.id.et_enddatatime:
                DatePickerDialog.newInstance((dialog, year, monthOfYear, dayOfMonth) -> {
                            etEnddatatime.setText(String.format(Locale.getDefault(),
                                    "%d-%d-%d", year, monthOfYear + 1, dayOfMonth));
                        }, Calendar.getInstance().get(Calendar.YEAR)
                        , Calendar.getInstance().get(Calendar.MONTH)
                        , Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                        .show(getFragmentManager(), "请选择开始时间");
                break;
        }
    }

    @Override
    public void showStockInfo(List<ShopStockInfo> shopStockInfos) {
        adapter.setData(shopStockInfos);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String setTitle() {
        return "库存";
    }

    @Override
    public IBasePresenter initPresenter() {

        presenter = new StockPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);
    }


    @OnClick(R.id.ib_stock_search)
    public void onClick() {
        if(etStartdatatime.getText().toString().contains("请选择")){
            startDate = "";
        }
        if(etEnddatatime.getText().toString().contains("请选择")){
            endDate = "";
        }
        presenter.loadStockInfo(InventoryApplication.getShopId(),startDate,
                endDate,"",
                etStockName.getText().toString());
    }
}
