package com.dealby.http;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpSender {
	private String url = "http://my.deal.by/cabinet/export_orders/xml/31036";
	private String params = "hash_tag=0ecc3ed1de227f51bf44ca80ae7abf6b";

	public HttpSender(String _url) {
		this.url = _url;
	}

	public HttpSender() {
	}

	public synchronized String sendHTTP(String query) throws Exception {

		if (query == null)
			query = this.params;

		HttpClient httpClient = new DefaultHttpClient();

		String url = this.url + "?" + query;

		Log.i("GET REQUEST", "GET REQUEST: " + url);

		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				entity.writeTo(out);
				out.close();
				String responseStr = out.toString();
	
				return responseStr;
				
			} else {
				return "";
			}

		} catch (Exception e) {
			Log.e("send HTTP",
					"Ошибка отправки функцией send HTTP! " + e.getMessage());
			return "";
		}
	}
}
