package com.javaproject1.petstore.controllers;
import java.util.Collection;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaproject1.petstore.entities.Product;
import com.javaproject1.petstore.services.ProductService;

import jakarta.validation.Valid;

// @RestController
// public class ProductController {

//     @Autowired
//     private ProductService productService;

//     // @RequestMapping(path = "/products", method = RequestMethod.GET)
//     @GetMapping("/products")
//     public Collection<Product> getAllProducts() {
//         return productService.getProducts();
//     }

//     // @RequestMapping(path = "/products", method = RequestMethod.POST)
//     @PostMapping("/products")
//     public String addProduct(@RequestBody @Valid Product p) {
//         return productService.add(p);
//     }

//     // @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
//     @GetMapping("/products/{id}")
//     public Product getProduct(@PathVariable("id") Integer pid) {
//         return productService.getProductById(pid);
//     }

//     // @RequestMapping(path = "/products/{id}", method = RequestMethod.DELETE)
//     @DeleteMapping("/products/{id}") 
//     public String delete(@PathVariable Integer id) {
//         return productService.deleteProduct(id);
//     }

//     // @RequestMapping(path = "/products/{id}", method = RequestMethod.PUT)
//     @PutMapping("/products/{id}")
//     public String update(@PathVariable Integer id, @RequestBody @Valid Product p) {
//         return productService.updateProduct(id, p);
//     }
// }

//Controller class will receive the request and will send the response
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PutMapping("/products/{id}/upload")
    public ResponseEntity<?> upload(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(productService.storeFile(id, file));
    }

    @GetMapping("/products/download/{filename}")
    public ResponseEntity<?> download(@PathVariable String filename){
        Resource resource = this.productService.downloadFile(filename);

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + filename + "\"")
                             .body(resource);
    }

    // // @RequestMapping(path = "/products", method = RequestMethod.GET)
    // @GetMapping("/products")
    // public ResponseEntity<?> getAllProducts(){
    // // System.out.println("Get method");
    // return ResponseEntity.ok(productService.getProducts());
    // //response is converted into JSON and then is send to the client
    // }

    // //Here the request is coming with the JSON data
    // //which we will read as the parameter of the mapped method
    // // @RequestMapping(path = "/products", method = RequestMethod.POST)
    // @PostMapping("/products")
    // public ResponseEntity<?> addProduct(@RequestBody @Valid Product p){
    // //@RequestBody annotation will read the JSON data from the request
    // //and convert it into the object of the class indicate in the method
    // parameter
    // // System.out.println("Post method");
    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(productService.add(p));
    // }

    // // @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    // @GetMapping("/products/{id}")
    // public ResponseEntity<?> getProduct(@PathVariable("id") Integer pid){
    // //@PathVariable annotation is used to map the value of the
    // //path variable in the URL with the method parameter
    // Product found = productService.getProductById(pid);
    // if(found == null){
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    // .body("Product with this id does not exists");
    // }
    // else{
    // return ResponseEntity.ok(found);
    // }
    // }

    // // @RequestMapping(path = "/products/{id}", method = RequestMethod.DELETE)
    // @DeleteMapping("/products/{id}")
    // public ResponseEntity<?> delete(@PathVariable Integer id){
    // Product found = productService.getProductById(id);
    // if(found == null){
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    // .body("Product with this id does not exists");
    // }
    // else{
    // return ResponseEntity.ok(productService.deleteProduct(id));
    // }
    // }

    // // @RequestMapping(path = "/products/{id}", method = RequestMethod.PUT)
    // @PutMapping("/products/{id}")
    // public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody
    // Product p){
    // Product found = productService.getProductById(id);
    // if(found == null){
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    // .body("Product with this id does not exists");
    // }
    // else{
    // p.setCreationTime(found.getCreationTime());
    // return ResponseEntity.ok(productService.updateProduct(id, p));
    // }
    // }
}