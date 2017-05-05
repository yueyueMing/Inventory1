package drawthink.com.inventory.data.remote.func;




import drawthink.com.inventory.data.remote.server.inventory.vo.LocalResponse;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <b>类名称：</b> ResponseTransformer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:25<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class ResponseTransformer<T> implements Observable.Transformer<Response<LocalResponse<T>>, T> {

    @Override
    public Observable<T> call(Observable<Response<LocalResponse<T>>> responseObservable) {
        return responseObservable
                .subscribeOn(Schedulers.io())
                .concatMap(new ResponseFunc<>())
                .concatMap(new LocalResponseFunc<>())
                .retryWhen(new RetryWithDelay(3,500))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
