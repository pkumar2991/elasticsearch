package com.example.elastic_search_playground.sec05.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

@Document(indexName = "garments")
@Mapping(mappingPath = "sec05/index-mapping.json")
public class Garment {

    @Id
    private String id;
    private String name;
    private List<String> color;
    private List<String> size;
    private Integer price;
    private String brand;
    private String occasion;
    private String neckStyle;
    private String material;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getColor() {
        return color;
    }
    public void setColor(List<String> color) {
        this.color = color;
    }
    public List<String> getSize() {
        return size;
    }
    public void setSize(List<String> size) {
        this.size = size;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getOccasion() {
        return occasion;
    }
    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }
    public String getNeckStyle() {
        return neckStyle;
    }
    public void setNeckStyle(String neckStyle) {
        this.neckStyle = neckStyle;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    @Override
    public String toString() {
        return "Garment [id=" + id + ", name=" + name + ", color=" + color + ", size=" + size + ", price=" + price
                + ", brand=" + brand + ", occasion=" + occasion + ", neckStyle=" + neckStyle + ", material=" + material
                + "]";
    }

    
}
