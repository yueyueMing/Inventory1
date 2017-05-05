package drawthink.com.inventory.functions.sell;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drawthink.com.inventory.InventoryApplication;
import drawthink.com.inventory.R;
import drawthink.com.inventory.component.AppComponent;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductAll;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductCategory;
import drawthink.com.inventory.functions.BaseActivity;
import drawthink.com.inventory.functions.IBasePresenter;
import drawthink.com.inventory.functions.sell.Presenter.SellPresenter;
import drawthink.com.inventory.functions.sell.adapter.ProductCategoryItem;
import drawthink.com.inventory.functions.sell.adapter.ProductDetailItem;
import drawthink.com.inventory.functions.sell.contract.SellContract;
import drawthink.com.inventory.utils.JumpUtil;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;
import rx.Observable;

/**
 * <b>类名称：</b> SellActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年02月23日 11:43<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public class SellActivity extends BaseActivity implements SellContract.View {
    @BindView(R.id.recycle_title)
    RecyclerView recycleTitle;
    @BindView(R.id.productsRecycler)
    RecyclerView productsRecycler;
    @BindView(R.id.queryEt)
    EditText queryEt;
    private SellPresenter presenter;
    private CommonRcvAdapter<ProductCategory> adapter;
    private CommonRcvAdapter<ProductAll> productAdapter;
    private List<ProductAll> sourceProductList;
    private List<ProductCategory> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ButterKnife.bind(this);
        initRecycleTitle();
        initProductRecycler();
        presenter.loadCategoryInfo();
        presenter.loadAllProductInfo(InventoryApplication.getShopId(), "all");
        queryEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ("".equals(charSequence)) {
                    productAdapter.setData(sourceProductList);
                } else {
                    Observable.from(sourceProductList)
                            .filter(productAll -> productAll.getName().contains(charSequence))
                            .toList()
                            .subscribe(productAlls -> {
                                productAdapter.setData(productAlls);
                            });
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void initProductRecycler() {
        productsRecycler.setLayoutManager(new GridLayoutManager(this, 5));
        MyItemDecoration decoration = new MyItemDecoration(5,5,5,5);
        productsRecycler.addItemDecoration(decoration);//设置各个方向的间隔
        productAdapter = new CommonRcvAdapter<ProductAll>(null) {

            @NonNull

            @Override
            public AdapterItem createItem(Object o) {
                return new ProductDetailItem(SellActivity.this);
            }
        };
        productsRecycler.setAdapter(productAdapter);

        RecyclerItemClickSupport.addTo(productsRecycler)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    ProductAll productAll = productAdapter.getData().get(position);
                    new JumpUtil(SellActivity.this, SellDetailActivity.class)
                            .extra("productId",productAll.getStockId()).start();
                });

    }


    private void initRecycleTitle() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleTitle.setLayoutManager(linearLayoutManager);
        adapter = new CommonRcvAdapter<ProductCategory>(null) {

            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new ProductCategoryItem(SellActivity.this);
            }
        };
        recycleTitle.setAdapter(adapter);
        recycleTitle.addItemDecoration(new VerticalDividerItemDecoration.Builder(this)
                .showLastDivider().build());
        RecyclerItemClickSupport.addTo(recycleTitle)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    clearCategorySelected();
                    ProductCategory productCategory = categories.get(position);
                    productCategory.setSelected(true);
                    adapter.setData(categories);
                    adapter.notifyDataSetChanged();
                    presenter.loadAllProductInfo(InventoryApplication.getShopId(),
                            productCategory.getId());
                });
    }

    private void clearCategorySelected() {
        for (ProductCategory category : categories) {
            category.setSelected(false);
        }
    }


    @Override
    public String setTitle() {
        return "商品销售";
    }

    @Override
    public IBasePresenter initPresenter() {
        presenter = new SellPresenter(this);
        return presenter;
    }

    @Override
    public void injectAppComponent(AppComponent appComponent) {
        appComponent.inject(this);

    }


    @Override
    public void showProductCategoryInfo(List<ProductCategory> categories) {
        this.categories = categories;
        adapter.setData(this.categories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showAllProductInfo(List<ProductAll> productAll) {
        sourceProductList = productAll;
        productAdapter.setData(productAll);
        productAdapter.notifyDataSetChanged();

    }
    //控制每个item距离紧挨item的距离
    class MyItemDecoration extends RecyclerView.ItemDecoration{
        int itemTop;
        int itemLeft;
        int itemBottom;
        int itemRight;

        public MyItemDecoration(int itemTop, int itemLeft, int itemBottom, int itemRight) {
            this.itemTop = itemTop;
            this.itemLeft = itemLeft;
            this.itemBottom = itemBottom;
            this.itemRight = itemRight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left=itemLeft;
            outRect.right=itemRight;
            outRect.bottom=itemBottom;
            outRect.top=itemTop;
        }
    }


}
