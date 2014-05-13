package com.dealby.adapters;

import java.util.List;

import com.dealby.ApplicationStatusController;
import com.dealby.R;
import com.dealby.models.Item;
import com.dealby.models.Items;
import com.dealby.models.Order;
import com.dealby.utils.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("unused")
public class ItemsAdapter extends BaseAdapter {

	private static LayoutInflater inflater = null;
	private int orderPos;
	private List<Item> items;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

	public ItemsAdapter(Activity activity , int pos) {
		this.orderPos = pos;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		items = ApplicationStatusController.getInstance().getSearchResultOrders()
		.getListOrder().get(orderPos).getItems().getListItem();
		
	}

	public int getCount() {
		return items.size();
	}

	public Item getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.lv_item, null);
		}

		TextView label_name = (TextView) vi.findViewById(R.id.label_name);
		TextView label_count = (TextView) vi.findViewById(R.id.label_count);
		TextView label_price = (TextView) vi.findViewById(R.id.label_price);
		ImageView list_image = (ImageView) vi.findViewById(R.id.list_image);

		Item item_details = getItem(position);

		String value = null;

		value = item_details.getName();
		if (value != null)
			label_name.setText(value);
		
		value = item_details.getQuantity();
		if (value != null)
		{
			double dbl = 0;
			try {
				dbl = Double.parseDouble(value);
			}
			catch (NumberFormatException ex) {
				Log.e("ItemsAdapter.StrToInt", ex.getMessage());
			}
			label_count.setText((int)dbl +  " pcs");
		}
		
		value = item_details.getPrice();
		if (value != null)
			label_price.setText(value +" "+ item_details.getCurrency());
		
//		value = item_details.getImage();
//		if (value != null){
//			ImageLoader
//					.getInstance()
//					.displayImage(
//							value,
//							list_image,
//							ApplicationStatusController.getInstance().getOptionsForCashImg(),
//							animateFirstListener);
//		} else {
//			ImageLoader
//					.getInstance()
//					.displayImage(
//							null,
//							list_image,
//							ApplicationStatusController.getInstance().getOptionsForCashImg(),
//							animateFirstListener);
//		}

		value = item_details.getImage();
		if (value != null){
		Picasso.with(parent.getContext())
	    .load(value)
	    .placeholder(R.drawable.emptyimg)
	    .error(R.drawable.emptyimg)
	    .resize(120, 120)
	    .into(list_image);
		} 
	
		return vi;
	}
}