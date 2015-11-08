/**
 * 
 */
package io.rong.app.photo;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author andrewlu 带选中效果的图片控件.
 */
public class CheckImageView extends ImageView {
	private final static LruCache<Integer, Bitmap> cache = new LruCache<Integer, Bitmap>(
			20);

	public CheckImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CheckImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CheckImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	private boolean checked = true;
	private int checkedColor = Color.argb(0x90, 0x0, 0x0, 0x0);
	private Bitmap selected_drawable = null, unselected_drawable = null;
	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public void setCheckedColor(int color) {
		this.checkedColor = color;
	}

	// 从缓存中查找图片.而不是每次都来重新解析图片.
	public void setCheckedResource(int selected_drawable, int unselect_drawable) {
		if (selected_drawable > 0) {
			if (cache.get(selected_drawable) == null) {
				this.selected_drawable = BitmapFactory.decodeResource(
						getResources(), selected_drawable);
				cache.put(selected_drawable, this.selected_drawable);
			}
		}
		if (unselect_drawable > 0) {
			if (cache.get(unselect_drawable) == null) {
				this.selected_drawable = BitmapFactory.decodeResource(
						getResources(), unselect_drawable);
				cache.put(unselect_drawable, this.unselected_drawable);
			}
		}
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (checked) {
			canvas.drawColor(checkedColor, Mode.DARKEN);
			if(selected_drawable!= null){
				canvas.drawBitmap(selected_drawable, 100, 100, paint);
			}
		} else {
			canvas.drawBitmap(unselected_drawable, 100, 100, paint);
		}
	}
}
