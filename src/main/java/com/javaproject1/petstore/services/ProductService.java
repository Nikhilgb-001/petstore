package com.javaproject1.petstore.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javaproject1.petstore.FileStorageProperties;
import com.javaproject1.petstore.entities.Product;
import com.javaproject1.petstore.repositories.ProductRepositry;

import org.springframework.core.io.Resource;

// @Service
// public class ProductService {
//     private Map<Integer, Product> productMap = new HashMap<>();
//     private AtomicInteger atomic = new AtomicInteger();

//     public ProductService() {
//         Product product1 = new Product();
//         product1.setId(atomic.incrementAndGet());
//         product1.setName("T-Shirt's For Pet Dogs");
//         product1.setPrice(500.85);
//         product1.setDescription("Quanlity T-Shirt For Pet Dog");
//         productMap.put(product1.getId(), product1);

//         Product product2 = new Product();
//         product2.setId(atomic.incrementAndGet());
//         product2.setName("Dog Food");
//         product2.setPrice(1200.30);
//         product2.setDescription("Quality Food For Pet Dogs");
//         productMap.put(product2.getId(), product2);
//     }

//     public Collection<Product> getProducts() {
//         return productMap.values();
//     }

//     public String add(Product p) {
//         p.setId(atomic.incrementAndGet());
//         productMap.put(p.getId(), p);
//         return "product added sucessfully";
//     }

//     public Product getProductById(Integer id) {
//         Product found = productMap.get(id);
//         if (found != null)
//             // return productMap.get(id); or
//             return found;
//         else
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product With this id dose not exist");
//     }

//     public String deleteProduct(Integer id) {
//         getProductById(id); // checking id the record exists or not
//         productMap.remove(id);
//         return "Product object deleted Successfully";
//     }

//     public String updateProduct(Integer id, Product p) {
//         getProductById(id);
//         p.setId(id);
//         productMap.put(id, p);
//         return "Product object updated Successfully";
//     }

// }

//Service class will contain the business logic which will be accessed from the controller
@Service
public class ProductService {

    @Autowired
    private ProductRepositry repository;

    private final Path rootLocation;

    // location where the upload is to be done and from the file will be downloaded
    public ProductService(FileStorageProperties properties) {
        this.rootLocation = Paths.get(properties.getUploadDir());

        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not create the directory for upload and download");
        }
    }

    public String storeFile(Integer id, MultipartFile file) {
        // 1. File is coming in the request which we need
        // to add in the uploads
        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));

        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not save the file");
        }

        // 2. Create the URL that will be used to download
        // the file and we will be updating for all the products

        // "http://localhost:8080" to get this part of the url =>
        // ServletUriComponentsBuilder.fromCurrentContextPath()
        String uploadedFileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/products/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        Product product = this.repository.findById(id).get();
        product.setId(id);
        product.setImageUrl(uploadedFileUrl);
        this.repository.save(product);
        return "File Uploaded Successfully";
    }

    public Resource downloadFile(String filename){
        Path file = rootLocation.resolve(filename);

        try{
            Resource resource = new UrlResource(file.toUri());

            return resource;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                              "Could not download the file");
        }
    }

    // public Iterable<Product> getProducts(){
    // return repository.findAll();
    // }

    // public Product add(Product p){
    // return repository.save(p);
    // }

    // public Product getProductById(Integer id){
    // return repository.findById(id).orElse(null);
    // }

    // public String deleteProduct(Integer id){
    // repository.deleteById(id);
    // return "Product Object Deleted Successfully";
    // }

    // public Product updateProduct(Integer id, Product p){
    // p.setId(id);
    // return repository.save(p);
    // }
}