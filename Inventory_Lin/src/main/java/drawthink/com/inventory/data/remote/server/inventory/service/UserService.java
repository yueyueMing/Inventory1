package drawthink.com.inventory.data.remote.server.inventory.service;

import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import drawthink.com.inventory.data.remote.server.inventory.vo.login.User;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <b>类名称：</b> UserService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年02月13日 14:51<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface UserService {
    String USER_SERVICE_URL = "user";
    /*
    登录接口
     */

    @GET(USER_SERVICE_URL + "/login/{mobile}/{validCode}")
    Observable<Response<LocalResponse<User>>> login(@Path("mobile") String mobile,
                                                    @Path("validCode") String validCode);

//    @POST("userMobileRegister.do")
//    @FormUrlEncoded
//    Observable<Response<LocalResponse<Void>>> register(@Field("nickName") String name,
//                                                       @Field("idCard") String identity,
//                                                       @Field("invitationMobile") String recommend,
//                                                       @Field("mobile") String mobile,
//                                                       @Field("vcode") String validCode,
//                                                       @Field("imsi") String imsi);
    /*
    已登录用户数据刷新
     */


    @GET(USER_SERVICE_URL + "/flash/{mobile}")
    Observable<Response<LocalResponse<User>>> splashDate(@Path("mobile") String mobile
    );


}
