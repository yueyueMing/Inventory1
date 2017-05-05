package drawthink.com.inventory.data.remote.server.inventory.service;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayForm;
import drawthink.com.inventory.data.remote.server.inventory.vo.pay.PayInfo;
import drawthink.com.inventory.data.remote.server.inventory.vo.statistics.Statistic;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <b>类名称：</b> PayService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> WANT <br/>
 * <b>修改人：</b> WANT <br/>
 * <b>修改时间：</b> 2017年03月02日 17:04<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */


public interface PayService {
    String USER_SERVICE_URL = "pay";

    /*
    记录统计信息
     */

    @GET(USER_SERVICE_URL + "/statistics/{shopId}")
    Observable<Response<LocalResponse<Statistic>>> getStatisticsInfo(@Path("shopId") String shopId,
                                                                     @Query("startDate") String startDate,
                                                                     @Query("endDate") String endDate,
                                                                     @Query("sellerId") int sellerId,
                                                                     @Query("productCode") String productCode,
                                                                     @Query("productName") String productName);
    /*
    记录支付信息
     */
    @POST(USER_SERVICE_URL)
    Observable<Response<LocalResponse<PayInfo>>> pay(@Body PayForm payForm);



}
