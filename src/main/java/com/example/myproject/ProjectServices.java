 package com.example.myproject;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProjectServices {
	@Autowired
	ProductRepository repo;

	public List<Product> getProducts() {
		return repo.findAll();
	}

//  WHEN THERE IS NO IMAGE PRESENT
//	public Product addProds(Product p) {
//		return repo.save(p);
//	}
	public Product getProdsById(int id) {
		return repo.findById(id).get();
	}

	public Product addProds(Product p, MultipartFile imageFile) throws IOException {
		p.setImageName(imageFile.getOriginalFilename());
		p.setImageType(imageFile.getContentType());
		p.setImageData(imageFile.getBytes());

		return repo.save(p);
	}

	public Product updateTheProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());

		return repo.save(product);
	}


	public void deleteprods(int id) {
		   repo.deleteById(id);
		
	}

}
