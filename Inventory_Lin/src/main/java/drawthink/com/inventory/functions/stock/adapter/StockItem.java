package drawthink.com.inventory.functions.stock.adapter;

import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.R;
import drawthink.com.inventory.data.remote.server.inventory.vo.stock.ShopStockInfo;
import drawthink.com.inventory.utils.DateTimeUtils;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> StockItem <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月06日 16:44<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class StockItem implements AdapterItem<ShopStockInfo> {
    @BindView(R.id.stock_data)
    TextView stockData;
    @BindView(R.id.stock_name)
    TextView stockName;
    @BindView(R.id.stock_number)
    TextView stockNumber;
    @BindView(R.id.stock_category)
    TextView stockCategory;
    @BindView(R.id.stock_state)
    TextView stockState;
    @BindView(R.id.stock_color)
    TextView stockColor;
    @BindView(R.id.stock_price)
    TextView stockPrice;
    @BindView(R.id.stock_sum)
    TextView stockSum;
    @BindView(R.id.stock_saleVolume)
    TextView stockSaleVolume;
    @BindView(R.id.stock_currentNum)
    TextView stockCurrentNum;
    @BindView(R.id.stock_sale)
    TextView stockSale;

    @Override
    public int getLayoutResId() {
        return R.layout.item_stock;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(ShopStockInfo shopStockInfo, int i) {
        stockData.setText(DateTimeUtils.pase(shopStockInfo.getDate()));
        stockName.setText(shopStockInfo.getProductName());
        stockNumber.setText(shopStockInfo.getProductCode());
        stockCategory.setText(shopStockInfo.getProductCategory());
        stockState.setText(shopStockInfo.getProductTye());
        stockColor.setText(shopStockInfo.getProductColor());
        stockPrice.setText(new DecimalFormat("¥#.##").format(shopStockInfo.getPrice()));
        stockSum.setText(String.format("%s", shopStockInfo.getInventoryNum()));
        stockSaleVolume.setText(String.format("%s", shopStockInfo.getSellNum()));
        stockCurrentNum.setText(String.format("%s", shopStockInfo.getLessNum()));
        stockSale.setText(new DecimalFormat("¥#.##").format(shopStockInfo.getTotal()));
    }
}
