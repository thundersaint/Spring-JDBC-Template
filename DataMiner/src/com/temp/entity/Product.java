/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.entity;

public class Product implements EntityInterface {
	public static int TITTLE_SIZE = 75 ;
	public static int PRODID_SIZE = 50 ;
	public static int PROMO_SIZE = 75 ;
	
	private String title ;
	private String description ;
	private double price ;
	private String product_url ;
	private String image_url ;
	private String product_id ;
	private double click_bid ;
	private String promotion ;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String title, String description, double price,
			String product_url, String image_url, String product_id,
			double click_bid, String promotion) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.product_url = product_url;
		this.image_url = image_url;
		this.product_id = product_id;
		this.click_bid = click_bid;
		this.promotion = promotion;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProduct_url() {
		return product_url;
	}
	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public double getClick_bid() {
		return click_bid;
	}
	public void setClick_bid(double click_bid) {
		this.click_bid = click_bid;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	
	public String toString() {
		return this.title +"\n"+ this.description +"\n"+ this.price  +"\n"+  this.product_url  
		+"\n"+  this.image_url  +"\n"+  this.product_id  +"\n"+  this.click_bid  +"\n"+  this.promotion ;
	}
}
