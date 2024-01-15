package response.getAllProduct;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse{

	@JsonProperty("total")
	private int total;

	@JsonProperty("limit")
	private int limit;

	@JsonProperty("skip")
	private int skip;

	@JsonProperty("products")
	private List<ProductsItem> products;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setSkip(int skip){
		this.skip = skip;
	}

	public int getSkip(){
		return skip;
	}

	public void setProducts(List<ProductsItem> products){
		this.products = products;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}