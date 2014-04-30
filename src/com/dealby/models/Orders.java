package com.dealby.models;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "orders")
public class Orders {

	@Attribute(name = "date")
	private String date;

	@ElementList(inline = true, name = "order")
	private List<Order> orders;

	public String getDate() {
		return this.date;
	}

	public List<Order> getListOrder() {
		return this.orders;
	}
	
	public void setListOrder(List<Order> ord) {
		this.orders = ord;
	}

}
