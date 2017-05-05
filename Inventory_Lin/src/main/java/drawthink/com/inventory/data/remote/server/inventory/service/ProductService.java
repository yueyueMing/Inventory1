package drawthink.com.inventory.data.remote.server.inventory.service;

import java.util.List;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductAll;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductCategory;
import drawthink.com.inventory.data.remote.server.inventory.vo.product.ProductDetail;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <b>类名称：</b> ProductService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 16:06<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface ProductService {

    String PRODUCT_SERVICE_URL = "product";
/*
获取商品列表
 */
    @GET(PRODUCT_SERVICE_URL + "/{shopId}/{category}")
    Observable<Response<LocalResponse<List<ProductAll>>>> getProductInfo(
            @Path("shopId") int shopId,
            @Path("category") String category);
/*
获取商品分类
 */

    @GET(PRODUCT_SERVICE_URL + "/category")
    Observable<Response<LocalResponse<List<ProductCategory>>>> getProductCategory();

    /*
    获取商品详情
     */
    @GET(PRODUCT_SERVICE_URL + "/detail/{productId}")
    Observable<Response<LocalResponse<ProductDetail>>> getProductDetail (@Path("productId") int productId);


}
