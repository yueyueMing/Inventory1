package drawthink.com.inventory.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.socks.library.KLog;

/**
 * <b>类名称：</b> DrawableLeftCenterTextView <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-11-15 上午11:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class DrawableTopCenterTextView extends TextView {

    public DrawableTopCenterTextView(Context context) {
        super(context);
    }

    public DrawableTopCenterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableTopCenterTextView(Context context, AttributeSet attrs,
                                     int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if(drawables != null){
            Drawable drawableTop = drawables[1];
            if(drawableTop != null){
                Paint.FontMetrics fm = getPaint().getFontMetrics();
                int textHeight = (int) Math.ceil(fm.descent - fm.ascent);
                int drawablePadding = getCompoundDrawablePadding();
                int drawableHeight = drawableTop.getIntrinsicHeight();
                float bodyHeight = textHeight + drawableHeight + drawablePadding;
                KLog.e("view height = "+getHeight());
                KLog.e("view text height = "+textHeight);
                KLog.e("view drawableHeight height = "+ drawableHeight);
                KLog.e("view translate Y = "+ (getHeight() - bodyHeight) / 2);

                canvas.translate(0, (getHeight() - bodyHeight) / 2);
            }
        }
        super.onDraw(canvas);
    }


}