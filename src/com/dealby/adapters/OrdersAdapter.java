package com.dealby.adapters;

import com.dealby.ApplicationStatusController;
import com.dealby.R;
import com.dealby.models.Order;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressWarnings("unused")
public class OrdersAdapter extends BaseAdapter {

	private static LayoutInflater inflater = null;
	private Boolean EditList;

	public OrdersAdapter(Activity activity) {
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return ApplicationStatusController.getInstance().getSearchResultOrders()
				.getListOrder().size();
	}

	public Order getItem(int position) {
		return ApplicationStatusController.getInstance().getSearchResultOrders()
				.getListOrder().get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.lv_order, null);
		}

		TextView tv_number_order = (TextView) vi
				.findViewById(R.id.tv_number_order);
		TextView tv_time = (TextView) vi.findViewById(R.id.tv_time);
		TextView tv_fio = (TextView) vi.findViewById(R.id.tv_fio);
		TextView tv_price = (TextView) vi.findViewById(R.id.tv_price);
		TextView tv_status = (TextView) vi.findViewById(R.id.tv_status);

		Order item_details = getItem(position);

		String value = null;

		value = item_details.getId();
		if (value != null)
			tv_number_order.setText("¹ "+value);

		value = item_details.getDate();
		if (value != null)
			tv_time.setText(value);

		value = item_details.getName();
		if (value != null)
			tv_fio.setText(value);

		value = item_details.getPriceUAH();
		if (value != null)
			tv_price.setText(value + " UAH");

		value = item_details.getState();
		if (value != null)
			tv_status.setText(value);

		return vi;
	}
}