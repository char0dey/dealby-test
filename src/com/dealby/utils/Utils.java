package com.dealby.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.dealby.ApplicationStatusController;
import com.dealby.R;
import com.dealby.adapters.ItemsAdapter;
import com.dealby.adapters.OrdersAdapter;
import com.dealby.models.Item;
import com.dealby.models.Order;
import com.dealby.models.Orders;

public class Utils {

	public static void ShowErrorDialog(String message, Activity activity) {
		new AlertDialog.Builder(activity)
				.setTitle(R.string.error_title)
				.setMessage(message)
				.setNeutralButton(R.string.ok_button,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {

							}

						}).show();
	}

	public static boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) ApplicationStatusController
				.getInstance().getCurrentActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;

	}

	public static void ordersFill() {
		try {

			ListView ordersItemsListView = (ListView) ApplicationStatusController
					.getInstance().getCurrentActivity()
					.findViewById(R.id.lv_orders);

			OrdersAdapter adapter = new OrdersAdapter(
					ApplicationStatusController.getInstance()
							.getCurrentActivity());

			TextView view = (TextView) ApplicationStatusController
					.getInstance().getCurrentActivity()
					.findViewById(R.id.main_header);

			StringBuilder headerText = new StringBuilder(4);
			headerText.append(ApplicationStatusController.getInstance()
					.getCurrentActivity().getResources()
					.getString(R.string.orders_title));
			headerText.append(" (");
			headerText.append(ApplicationStatusController.getInstance()
					.getSearchResultOrders().getListOrder().size());
			headerText.append(")");

			if (view != null) {
				view.setText(headerText);
				view.setVisibility(TextView.VISIBLE);
			}

			try {
				ordersItemsListView.setAdapter(adapter);
			} catch (Exception e) {
				Log.e("ordersFill", "Adapter = " + e.getMessage());

			}

		} catch (Exception e) {
			Log.e("ordersFill", e.getMessage());

		}

	}

	public static void itemsFill(int pos) {
		try {

			ListView itemsListView = (ListView) ApplicationStatusController
					.getInstance().getCurrentActivity()
					.findViewById(R.id.lv_items);

			ItemsAdapter adapter = new ItemsAdapter(ApplicationStatusController
					.getInstance().getCurrentActivity(), pos);

			TextView view = (TextView) ApplicationStatusController
					.getInstance().getCurrentActivity()
					.findViewById(R.id.main_header);

			StringBuilder headerText = new StringBuilder(4);
			headerText.append(ApplicationStatusController.getInstance()
					.getCurrentActivity().getResources()
					.getString(R.string.items_title));
			headerText.append(" (");
			headerText.append(ApplicationStatusController.getInstance()
					.getSearchResultOrders().getListOrder().get(pos).getItems()
					.getListItem().size());
			headerText.append(")");

			if (view != null) {
				view.setText(headerText);
				view.setVisibility(TextView.VISIBLE);
			}

			try {
				itemsListView.setAdapter(adapter);
			} catch (Exception e) {
				Log.e("itemsFill", "Adapter = " + e.getMessage());

			}

		} catch (Exception e) {
			Log.e("itemsFill", e.getMessage());

		}

	}

	public static void searchInOrders(String searchStr) throws Exception{
		List<Order> listOrder = ApplicationStatusController.getInstance()
				.getOrders().getListOrder();
		ArrayList<Order> resOrders = new ArrayList<Order>();
		for (Order d : listOrder) {
			if (d.getName()!=null && d.getPhone()!=null && d.getId()!=null)
			if (d.getId().contains(searchStr)
					|| d.getName().contains(searchStr)
					|| d.getPhone().contains(searchStr)
					|| isItemsContains(searchStr, d))
				resOrders.add(d);
		}
		Orders orders = new Orders();
		orders.setListOrder(resOrders);
		ApplicationStatusController.getInstance().setSearchResultOrders(orders);
	}

	public static boolean isItemsContains(String str, Order listObj) throws Exception {
		List<Item> testres = listObj.getItems().getListItem();
		int i = 0;
		for (Item d : testres) {
			if (d.getName()!=null && d.getSku()!=null)
			if (d.getName().contains(str) || d.getSku().contains(str))
				i++;
		}
		if (i > 0)
			return true;
		else
			return false;
	}

}