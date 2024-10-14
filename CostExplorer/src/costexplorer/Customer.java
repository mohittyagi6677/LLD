package costexplorer;
import java.util.*;

public class Customer {
	String id;
	String name;
	List<Product> products;
	
	public Customer(String id, String name, List<Product> products) {
		this.id = id;
		this.name = name;
		this.products = products;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Product> getProducts() {
		return products;
	}
	
	

}
