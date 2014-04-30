package com.dealby;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.widget.ProgressBar;

import com.dealby.models.Orders;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ApplicationStatusController {

	private Activity currentActivity;
	private static ApplicationStatusController instance;
	private ProgressBar progresbarMR;
	private ExecutorService executorService;
	private Orders searchResultOrders;
	private Orders orders;
	private DisplayImageOptions optionsForCashImg;

	public static ApplicationStatusController getInstance() {
		if (instance == null) {
			instance = new ApplicationStatusController();
		}
		return instance;
	}

	public ExecutorService getExecutorService() {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(10);
		}
		return executorService;
	}

	public DisplayImageOptions getOptionsForCashImg() {

		if (optionsForCashImg == null) {
			optionsForCashImg = new DisplayImageOptions.Builder()
					.showImageOnLoading(R.drawable.loading)
					.showImageForEmptyUri(R.drawable.emptyimg)
					.showImageOnFail(R.drawable.emptyimg).cacheInMemory(true)
					.cacheOnDisc(true)
					.displayer(new RoundedBitmapDisplayer(20))
					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
					.build();
		}
		return optionsForCashImg;
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public ProgressBar getProgresbarMR() {
		return progresbarMR;
	}

	public void setProgresbarMR(ProgressBar progresbarMR) {
		this.progresbarMR = progresbarMR;
	}

	public Orders getSearchResultOrders() {
		return searchResultOrders;
	}

	public void setSearchResultOrders(Orders obj) {
		this.searchResultOrders = obj;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders obj) {
		this.orders = obj;
	}

}
