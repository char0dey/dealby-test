package com.dealby;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.widget.ProgressBar;

import com.dealby.models.Orders;

public class ApplicationStatusController {

	private Activity currentActivity;
	private static ApplicationStatusController instance;
	private ProgressBar progresbarMR;
	private ExecutorService executorService;
	private Orders searchResultOrders;
	private Orders orders;

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
