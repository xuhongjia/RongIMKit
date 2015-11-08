package io.rong.app.photo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter implements View.OnClickListener
{
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	private  final Set<Integer> clickableIds = new HashSet<Integer>();

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		convert(viewHolder, getItem(position));
		View retView = viewHolder.getConvertView();

		for(Integer id: clickableIds){
			View item = retView.findViewById(id);
			item.setOnClickListener(this);
			item.setTag(getItem(position));
		}
		return retView;

	}

	public abstract void convert(ViewHolder helper, T item);

	public  void onItemClick(int rId, T data){};
	public void onItemClick(View v, T data){};

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent)
	{
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position,clickableIds);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(clickableIds.contains(id)){
			onItemClick(id, (T) v.getTag());
			onItemClick(v,(T)v.getTag());
		}
	}

}
