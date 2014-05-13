package com.dealby.activities;

import com.dealby.ApplicationStatusController;
import com.dealby.R;
import com.dealby.gestures.OnSwipeTouchListener;
import com.dealby.models.Order;
import com.dealby.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsOrderActivity extends Activity {

	private int orderPos;
	private int countOrders;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_order);

		ApplicationStatusController.getInstance().setCurrentActivity(this);

		if (orderPos == 0) {
			Intent intent = getIntent();
			orderPos = intent.getIntExtra("orderPos", 0);
		}
		fillDetails(orderPos);

		final View listLayout = findViewById(R.id.listLayout);
		listLayout.setOnTouchListener(swipeTouchListener);

		final ListView listview = (ListView) findViewById(R.id.lv_items);
		listview.setOnTouchListener(swipeTouchListener);

		final View mainLayout = findViewById(R.id.mainLayout);
		mainLayout.setOnTouchListener(swipeTouchListener);

		countOrders = ApplicationStatusController.getInstance().getSearchResultOrders()
				.getListOrder().size();
	}

	private OnSwipeTouchListener swipeTouchListener = new OnSwipeTouchListener() {

		public void onSwipeTop() {
			Toast.makeText(
					ApplicationStatusController.getInstance()
							.getCurrentActivity(), "onSwipeTop",
					Toast.LENGTH_LONG).show();
		}

		public void onSwipeRight() {
			if (orderPos > 0) {
				orderPos--;
				fillDetails(orderPos);
			}
		}

		public void onSwipeLeft() {
			if (orderPos < countOrders) {
				orderPos++;
				fillDetails(orderPos);
			}
		}

		public void onSwipeBottom() {
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.details_order, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		ProgressBar bar = (ProgressBar) findViewById(R.id.progress_load);
		ApplicationStatusController.getInstance().setProgresbarMR(bar);
	}

	private void fillDetails(int pos) {
		TextView tv_number_order = (TextView) findViewById(R.id.tv_number_order);
		TextView tv_fio = (TextView) findViewById(R.id.tv_fio);
		TextView tv_time = (TextView) findViewById(R.id.tv_time);
		TextView tv_phone = (TextView) findViewById(R.id.tv_phone);
		TextView tv_address = (TextView) findViewById(R.id.tv_address);
		TextView tv_mail = (TextView) findViewById(R.id.tv_mail);

		Order details = ApplicationStatusController.getInstance().getSearchResultOrders()
				.getListOrder().get(pos);

		if (details == null) {
			Toast.makeText(
					ApplicationStatusController.getInstance()
							.getCurrentActivity(), "Warning! No Data...",
					Toast.LENGTH_LONG).show();
			return;
		}

		tv_number_order.setText(details.getId());
		tv_fio.setText(details.getName());
		tv_time.setText(details.getDate());
		tv_phone.setText(details.getPhone());
		tv_address.setText(details.getAddress());
		tv_mail.setText(details.getEmail());

		Utils.itemsFill(pos);
	}

}
