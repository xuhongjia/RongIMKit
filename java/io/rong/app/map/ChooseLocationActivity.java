package io.rong.app.map;

import io.rong.imkit.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;

public class ChooseLocationActivity extends FragmentActivity implements
		View.OnClickListener, LocationSource, AMapLocationListener {
	private TextView title_bar_left;
	private TextView titleTextView;
	private TextView title_bar_right;
	private SupportMapFragment chooseLocationFragment;
	private LatLng selectPoint;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_select_location);

		title_bar_left = (TextView) findViewById(R.id.title_bar_left);
		titleTextView = (TextView) findViewById(R.id.title_bar_center);
		title_bar_right = (TextView) findViewById(R.id.title_bar_rigth);
		chooseLocationFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.chooseLocationFragment);
		titleTextView.setText("选择您的位置");
		title_bar_left.setOnClickListener(this);
		title_bar_right.setOnClickListener(this);
		setupMap();
	}

	private void setupMap() {
		AMap aMap = chooseLocationFragment.getMap();
		// 自定义系统定位小蓝点
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_gd));// 设置小蓝点的图标
		myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
		// myLocationStyle.anchor(int,int)//设置小蓝点的锚点
		myLocationStyle.strokeWidth(0.2f);// 设置圆形的边框粗细
		aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);//
		
		chooseLocationFragment.getMap().setOnMapClickListener(
				new AMap.OnMapClickListener() {

					@Override
					public void onMapClick(LatLng latlng) {
						// TODO Auto-generated method stub
						selectPoint = latlng;
					}
				});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.title_bar_rigth) {
			Intent data = new Intent();
			data.putExtra("latlng", selectPoint);
			setResult(RESULT_OK, data);
			finish();
		} else if (v.getId() == R.id.title_bar_left) {
			setResult(RESULT_CANCELED);
			finish();
		}
	}

	@Override
	public void activate(OnLocationChangedListener arg0) {
		mListener = arg0;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
			 * API定位采用GPS和网络混合定位方式
			 * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
			 */
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
	}

	@Override
	public void onLocationChanged(Location aLocation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		// TODO Auto-generated method stub
		if (mListener != null && aLocation != null) {
			mListener.onLocationChanged(aLocation);// 显示系统小蓝点
		}
	}

}
