package drawthink.com.inventory.functions.sell.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.R;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductCategory;
import drawthink.com.inventory.functions.sell.Presenter.SellPresenter;
import drawthink.com.inventory.functions.sell.contract.SellContract;
import kale.adapter.item.AdapterItem;

/**
 * <b>类名称：</b> ProductCategoryItem <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月24日 16:59<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class ProductCategoryItem implements AdapterItem<ProductCategory> {
    @BindView(R.id.categoryTv)
    TextView categoryTv;
    @BindView(R.id.categoryLayout)
    LinearLayout categoryLayout;
    private Context context;
    SellPresenter presenter;

    public ProductCategoryItem(Context context) {
        this.context = context;
        presenter = new SellPresenter((SellContract.View) context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_product_category;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setViews() {
        categoryLayout.setLayoutParams(new ViewGroup.LayoutParams(ScreenUtils.getScreenWidth() / 6, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void handleData(ProductCategory productCategory, int i) {
        if (productCategory != null) {
            categoryTv.setText(productCategory.getTitle());
        } else {
            ToastUtils.showShortToast("未获取到商品分类");
        }
        if(productCategory.isSelected()){
            categoryTv.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            categoryTv.setBackground(context.getResources().getDrawable(R.drawable.tab_menu_hover_bj));
        }else{
            categoryTv.setTextColor(context.getResources().getColor(R.color.dark_gray));
            categoryTv.setBackground(context.getResources().getDrawable(R.drawable.tab_menu_bj));
        }
    }
}
