package request.addProductBody;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Body{

	@JsonProperty("discountPercentage")
	private Object discountPercentage;

	@JsonProperty("thumbnail")
	private String thumbnail;

	@JsonProperty("images")
	private List<String> images;

	@JsonProperty("price")
	private int price;

	@JsonProperty("rating")
	private Object rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("stock")
	private int stock;

	@JsonProperty("category")
	private String category;

	@JsonProperty("brand")
	private String brand;

	public void setDiscountPercentage(Object discountPercentage){
		this.discountPercentage = discountPercentage;
	}

	public Object getDiscountPercentage(){
		return discountPercentage;
	}

	public void setThumbnail(String thumbnail){
		this.thumbnail = thumbnail;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public void setImages(List<String> images){
		this.images = images;
	}

	public List<String> getImages(){
		return images;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setRating(Object rating){
		this.rating = rating;
	}

	public Object getRating(){
		return rating;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public int getStock(){
		return stock;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}
}