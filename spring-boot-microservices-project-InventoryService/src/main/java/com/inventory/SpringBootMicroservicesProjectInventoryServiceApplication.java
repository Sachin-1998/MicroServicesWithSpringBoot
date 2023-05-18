package com.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inventory.Repository.InventoryRepo;
import com.inventory.entity.Inventory;

@SpringBootApplication
public class SpringBootMicroservicesProjectInventoryServiceApplication {

	@Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo)
	{
		
		return args->{ ;
		Inventory inventory1=new Inventory();
		inventory1.setSkuCode("Iphone_13");
		inventory1.setQuantity(100);
		Inventory inventory2=new Inventory();
		inventory2.setSkuCode("Iphone_11");
		inventory2.setQuantity(0);
		inventoryRepo.save(inventory1);
		inventoryRepo.save(inventory2);
		
		
		};
		
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservicesProjectInventoryServiceApplication.class, args);
		
	}

}
