package com.dealby.activities;

import com.dealby.ApplicationStatusController;
import com.dealby.R;
import com.dealby.asynctasks.ListViewLoaderTask;
import com.dealby.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OrdersActivity extends Activity {

	// private static String TAG = "OrdersActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orders);
		ApplicationStatusController.getInstance().setCurrentActivity(this);

		final TextView editText = (TextView) findViewById(R.id.txbSearch);

		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub

				if (actionId == EditorInfo.IME_ACTION_SEARCH) {

					try {
						Utils.searchInOrders(editText.getText().toString());
						Utils.ordersFill();
						addOpenActionToItem();
					} catch (Exception e) {
						Utils.ShowErrorDialog(e.getMessage(),
								ApplicationStatusController.getInstance()
										.getCurrentActivity());
					}

					// Hide KeyBoard on Click Button
					InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

					inputManager.hideSoftInputFromWindow(getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);

					// Hide KeyBoard
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.orders, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		ApplicationStatusController.getInstance().setCurrentActivity(this);
		ProgressBar bar = (ProgressBar) findViewById(R.id.progress_load);
		ApplicationStatusController.getInstance().setProgresbarMR(bar);

		if (Utils.isNetworkConnected()) {
			if (ApplicationStatusController.getInstance().getOrders() == null) {

				ApplicationStatusController.getInstance().getProgresbarMR()
						.setVisibility(View.VISIBLE);

				ListViewLoaderTask asynctask = new ListViewLoaderTask();
				asynctask.execute();
				addOpenActionToItem();
			} else {
				Utils.ordersFill();
				addOpenActionToItem();
			}
		} else {
			Toast.makeText(
					ApplicationStatusController.getInstance()
							.getCurrentActivity(),
					getString(R.string.no_network_connection),
					Toast.LENGTH_LONG).show();
		}
	};

	private void addOpenActionToItem() {

		ListView view = (ListView) ApplicationStatusController.getInstance()
				.getCurrentActivity().findViewById(R.id.lv_orders);

		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent(ApplicationStatusController
						.getInstance().getCurrentActivity(),
						DetailsOrderActivity.class);
				intent.putExtra("orderPos", position);
				startActivity(intent);
			}

		});
	}

}
