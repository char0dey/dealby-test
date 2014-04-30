package com.dealby.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "order")
public class Order {

	@Attribute(required = false, name = "id")
	private String id;

	@Attribute(required = false, name = "state")
	private String state;

	@Element(required = false, name = "name")
	private String name;

	@Element(required = false, name = "phone")
	private String phone;

	@Element(required = false, name = "email")
	private String email;

	@Element(required = false, name = "date")
	private String date;

	@Element(required = false, name = "address")
	private String address;

	@Element(required = false, name = "index")
	private String index;

	@Element(required = false, name = "paymentType")
	private String paymentType;

	@Element(required = false, name = "deliveryType")
	private String deliveryType;

	@Element(required = false, name = "priceUAH")
	private String priceUAH;

	@Element(required = false, name = "items")
	private Items items;

	public String getId() {
		return this.id;
	};

	public String getState() {
		return this.state;
	};

	public String getName() {
		if (this.name == null) this.name = "";
		return this.name;
	};

	public String getPhone() {
		if (this.phone == null) this.phone = "";
		return this.phone;
	};

	public String getEmail() {
		return this.email;
	};

	public String getDate() {
		return this.date;
	};

	public String getAddress() {
		return this.address;
	};

	public String getIndex() {
		return this.index;
	};

	public String getPaymentType() {
		return this.paymentType;
	};

	public String getDeliveryType() {
		return this.deliveryType;
	};

	public String getPriceUAH() {
		return this.priceUAH;
	};

	public Items getItems() {
		return this.items;
	};

}
