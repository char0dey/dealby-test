package com.dealby.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item")
public class Item {
	
	@Attribute(required=false , name="id")
    private String id;
	@Element(required=false , name = "external_id")
	private String external_id;
	@Element(required=false , name = "name")
	private String name;
	@Element(required=false , name = "quantity")
	private String quantity;
	@Element(required=false , name = "currency")
	private String currency;
	@Element(required=false , name = "image")
	private String image;
	@Element(required=false , name = "url")
	private String url;
	@Element(required=false , name = "price")
	private String price;
	@Element(required=false , name = "sku", type=String.class)
	private String sku;
	
    public String getId() 
    {
    	return this.id;
    };
    
    public String getExternal_id() 
    {
    	return this.external_id;
    };
	
    public String getName() 
    {
    	if (this.name == null) this.name = "";
    	return this.name;
    };
	
    public String getQuantity() 
    {
    	return this.quantity;
    };
	
    public String getCurrency() 
    {
    	return this.currency;
    };
	
    public String getImage() 
    {
    	return this.image;
    };
	
    public String getUrl() 
    {
    	return this.url;
    };
	
    public String getPrice() 
    {
    	return this.price;
    };
	
    public String getSku() 
    {
    	if (this.sku == null) this.sku = "";
    	return this.sku;
    };
}
