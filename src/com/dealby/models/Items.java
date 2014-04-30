package com.dealby.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "items")
public class Items {

	@ElementList(inline = true, name = "item")
	private List<Item> item;

	public List<Item> getListItem() {
		return this.item;
	};

}
