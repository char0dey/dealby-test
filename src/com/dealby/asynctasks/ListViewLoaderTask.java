package com.dealby.asynctasks;

import java.io.Reader;
import java.io.StringReader;

import org.simpleframework.xml.core.Persister;

import com.dealby.ApplicationStatusController;
import com.dealby.R;
import com.dealby.http.HttpSender;
import com.dealby.models.Orders;
import com.dealby.utils.Utils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class ListViewLoaderTask extends AsyncTask<Object, Void, Orders> {
	public ListViewLoaderTask() {
	}

	private String TAG = "ListViewLoaderTask";

	@Override
	protected Orders doInBackground(Object... params) {

		String response = null;
		Orders resOrders = null;

		HttpSender httpSend = new HttpSender();
		try {
			response = httpSend.sendHTTP(null);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

		if (response == null)
			return null;

		try {
			Reader reader = new StringReader(response);
			Persister serializer = new Persister();
			resOrders = serializer.read(Orders.class, reader, false);
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return resOrders;
	}

	@Override
	protected void onPostExecute(Orders result) {

		try {

			if (result == null) {
				Utils.ShowErrorDialog(
						ApplicationStatusController
								.getInstance()
								.getCurrentActivity()
								.getResources()
								.getString(
										R.string.retrieving_data_trouble_message),
						ApplicationStatusController.getInstance()
								.getCurrentActivity());

				ApplicationStatusController.getInstance().getProgresbarMR()
						.setVisibility(View.GONE);

				return;
			}

			ApplicationStatusController.getInstance().setSearchResultOrders(result);
			ApplicationStatusController.getInstance().setOrders(result);

			Utils.ordersFill();

			ApplicationStatusController.getInstance().getProgresbarMR()
					.setVisibility(View.GONE);

		} catch (Exception e) {
			Log.e(TAG, "onPostExecute " + e.getMessage());
		}

	}
}
