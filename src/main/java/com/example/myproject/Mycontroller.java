package com.example.myproject;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class Mycontroller {
	@Autowired
	ProjectServices service;

	@RequestMapping("/")

	public String get() {
		return "hello bro!";
	}

	@RequestMapping("/products")
	public List<Product> giveProds() {
		return service.getProducts();

	}

	/* When there is no image */
//	@PostMapping("/addproducts")
//	public Product addproducts(@RequestBody Product p) {
//		System.out.println("product details are:" + p);
//		return service.addProds(p);
//	}
	@RequestMapping("/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {

		Product product = service.getProdsById(id);
		if (product != null)
			return new ResponseEntity<>(product, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

//	when there is an image 
	@PostMapping("/addproducts")
	public ResponseEntity<?> addProductId(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
		try {
			Product p1 = service.addProds(product, imageFile);
			return new ResponseEntity<>(p1, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/products/{id}/image")
	public ResponseEntity<byte[]> getProductImagesById(@PathVariable int id) {
		Product product = service.getProdsById(id);
		byte[] imagefile = product.getImageData();

		return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imagefile);

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<String> updatepro(@PathVariable int id, @RequestPart Product product,
			@RequestPart MultipartFile imageFile) {
		Product pro = null;
		try {
			pro = service.updateTheProduct(id, product, imageFile);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
		if (pro != null) {
			return new ResponseEntity<>("Updated the product", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteprod(@PathVariable int id){
		Product product= service.getProdsById(id);
		if(product !=null) {
		   service.deleteprods(id);
		   return new ResponseEntity<>("Deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error no product found",HttpStatus.BAD_REQUEST);
		}
		
	}
//	// MASS INSERTION
//	@PostMapping("/addproducts")
//	public List<Product> addproducts(@RequestBody List<Product> p) {
//		System.out.println("product details are:" + p);
//		for (Product product : p) {
//			service.addProds(product);
//		}
//		return p;
//	}

}
