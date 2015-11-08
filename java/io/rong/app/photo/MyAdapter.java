package io.rong.app.photo;

import io.rong.imkit.R;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

public class MyAdapter extends CommonAdapter<String> {

	public List<String> mSelectedImage = new ArrayList<String>();

	private String mDirPath;
	private int maxPic;

	public MyAdapter(Context context, List<String> mDatas, int itemLayoutId, String dirPath, int maxPic) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		this.maxPic = maxPic;
	}

	public ArrayList<String> getPicList() {
		if (mSelectedImage == null) {
			mSelectedImage = new ArrayList<String>();
		}
		Log.e("list", mSelectedImage.toString());
		return (ArrayList<String>) mSelectedImage;
	}

	public void setReset() {
		mSelectedImage.clear();
	}

	private final static int TAG_HOLDER = 0x7f02000a;

	@Override
	public void convert(final ViewHolder helper, final String item) {

		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);
		helper.setViewClickable(R.id.id_item_image);

		ImageView mImageView = helper.getView(R.id.id_item_image);
		CheckBox mSelect = helper.getView(R.id.id_item_select);
		mImageView.setTag(TAG_HOLDER, helper);

		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setChecked(true);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		} else {
			mSelect.setChecked(false);
			mImageView.setColorFilter(null);
		}
	}

	@Override
	public void onItemClick(View view, String item) {
		ImageView mImageView = (ImageView) view;
		ViewHolder holder = (ViewHolder) mImageView.getTag(TAG_HOLDER);
		CheckBox mSelect = holder.getView(R.id.id_item_select);

		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelectedImage.remove(mDirPath + "/" + item);
			mSelect.setChecked(false);
			mImageView.setColorFilter(null);
		} else {
			if (mSelectedImage.size() < maxPic) {
				mSelectedImage.add(mDirPath + "/" + item);
				mSelect.setChecked(true);
				mImageView.setColorFilter(Color.parseColor("#77000000"));
			} else {
				// Toast.makeText(mContext, "最多显示" + maxPic + "张",
				// Toast.LENGTH_SHORT).show();
				Toast.makeText(mContext, "最多显示" + 9 + "张", Toast.LENGTH_SHORT).show();
			}
		}
		Log.i("size", "count-->" + mSelectedImage.size());
	}
}
