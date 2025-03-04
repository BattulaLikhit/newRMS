package com.example.myproject;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean productAvailable;
    private String brand;
    private String category;
    private String description;
    private String name;
    private int price;
    private int stockQuantity;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
    private Date releaseDate;
    String ImageName;
	String ImageType;
	@Lob
	private byte[] ImageData;
    public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}

    public String getImageType() {
		return ImageType;
	}
	public void setImageType(String imageType) {
		ImageType = imageType;
	}
	public byte[] getImageData() {
		return ImageData;
	}
	public void setImageData(byte[] imageData) {
		ImageData = imageData;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isProductAvailable() {
		return productAvailable;
	}
	public void setProductAvailable(boolean productAvailable) {
		this.productAvailable = productAvailable;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productAvailable=" + productAvailable + ", brand=" + brand + ", category="
				+ category + ", description=" + description + ", name=" + name + ", price=" + price + ", stockQuantity="
				+ stockQuantity + ", releaseDate=" + releaseDate + "]";
	}
	
}
