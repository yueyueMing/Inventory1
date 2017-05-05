package drawthink.com.inventory.functions.sell.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.R;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductAll;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> ProductDetailItem <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月27日 15:40<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class ProductDetailItem implements AdapterItem<ProductAll> {
    @BindView(R.id.productIv)
    ImageView productIv;
    @BindView(R.id.productNameTv)
    TextView productNameTv;
    @BindView(R.id.productPriceTv)
    TextView productPriceTv;
    @BindView(R.id.productSellNumTv)
    TextView productSellNumTv;
    private Context context;

    public ProductDetailItem(Context context) {

        this.context = context;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_sell_detail;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(ProductAll productAll, int i) {
        if (productAll != null) {
            productNameTv.setText(productAll.getName());
            productPriceTv.setText("¥:" + productAll.getPrice());
            productSellNumTv.setText("销量:"+productAll.getSellNum());
            List<String> image = productAll.getImage();
            Glide.with(context).load(image.get(0)).into(productIv);
        } else {
            ToastUtils.showShortToast("未获取到商品列表......");
        }

    }


}
