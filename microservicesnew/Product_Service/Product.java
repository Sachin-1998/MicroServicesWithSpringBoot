package java.com.product.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Document("Product_info")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Product 
{
	@Id
	private String product_id;
	private String product_name;
	private String product_description;
	private Double product_price;
	
	

}
