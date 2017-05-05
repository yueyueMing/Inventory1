package drawthink.com.inventory.functions.cash;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.data.remote.server.inventory.vo.shop.ShopSeller;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> SellerItem <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月08日 15:15<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class SellerItem implements AdapterItem<ShopSeller> {
    @BindView(android.R.id.text1)
    TextView textView;
    @Override
    public int getLayoutResId() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void setViews() {
    }

    @Override
    public void handleData(ShopSeller ShopSeller, int i) {
        textView.setText(ShopSeller.getName());
    }
}
