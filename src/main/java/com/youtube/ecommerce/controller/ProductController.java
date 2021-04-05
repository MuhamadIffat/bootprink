package com.youtube.ecommerce.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.ecommerce.Exeption.ResourceNotFoundException;
import com.youtube.ecommerce.Repository.CategoryRepo;
import com.youtube.ecommerce.Repository.ProductRepo;
import com.youtube.ecommerce.domain.Message;
import com.youtube.ecommerce.dto.ListProduct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.ecommerce.model.Products;
import com.youtube.ecommerce.model.User;
import com.youtube.ecommerce.service.ProductService.ProductServices;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    CategoryRepo cateRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductServices productService;

    @Autowired
    ServletContext context;

//    @GetMapping("getListProducts")
//    public List<ListProduct> list() {
//        return productService.getAll();
//    }

    @RequestMapping("/getAllProducts")
    public List<Products> getAllPRoducts() {
        System.out.println("Get all Products...");

        List<Products> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);

        return products;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductByCode(@PathVariable Long id)
            throws ResourceNotFoundException {
        Products products = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Products not found for this id :: " + id));
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping("/getProductsByCategory")
    public List<Products> getProductsByCategory(@RequestBody HashMap<String, String> request) {
        String category_id = request.get("cat_id");
        return productService.getProductsByCategory(category_id);
    }

    @GetMapping("/products/find/{id}")
    public ResponseEntity<Products> getProducstById(@PathVariable("id") Long id) {
        Products products = productService.getProductsById(id);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/5/{idCategory}")
    public ResponseEntity<List<Products>> listCategory(@PathVariable String idCategory) {

        List<Products> products = productRepo.findByCateg(idCategory);

        return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public Products createsProducts(@RequestBody Products products) {
        return productRepo.save(products);
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        productService.deleteProducts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/products/delete")
    public ResponseEntity<String> deleteAllProducts() {
        System.out.println("Delete All Scategories...");
        productRepo.deleteAll();
        return new ResponseEntity<>("All Products have been deleted!", HttpStatus.OK);
    }

    @PutMapping("/products/update")
    public ResponseEntity<Products> updateProducts(@RequestBody Products products) {
        Products updateProducts = productService.updateProducts(products);
        return new ResponseEntity<>(updateProducts, HttpStatus.OK);
    }

    @PatchMapping("/products/5/{id}")
    public ResponseEntity<Products> updateProducts(@PathVariable("id") Long id) {
        System.out.println("Update SCategorie with Code  = " + id + "...");

        Optional<Products> productInfo = productRepo.findById(id);

        if (productInfo.isPresent()) {
            Products products = productInfo.get();
            products.setStock(products.getStock() + 1);

            return new ResponseEntity<>(productRepo.save(products), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateArticle(@PathVariable("id") long id, @RequestBody Products productParam) {
        System.out.println("Update Product with ID = " + id + "...");
        Optional<Products> productInfo = productRepo.findById(id);
        if (productInfo.isPresent()) {
            Products products = productInfo.get();
            products.setName(productParam.getName());
            products.setId_category(productParam.getId_category());
            products.setPrice(productParam.getPrice());
            products.setStock(productParam.getStock());
            return new ResponseEntity<>(productRepo.save(productParam), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getimage/{img_name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable("img_name") String img_name) throws IOException {
        InputStream in = getClass().getResourceAsStream("/images/" + img_name);
        return IOUtils.toByteArray(in);
    }

    @PostMapping("/upload")
    public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("product") String product) throws JsonParseException, JsonMappingException, Exception {
        System.out.println("Ok .............");
        Products prod = new ObjectMapper().readValue(product, Products.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newfileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
        File serverFile = new File(context.getRealPath("/Images/" + File.separator + newfileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
            System.out.println("bersail");

        } catch (Exception e) {
            e.printStackTrace();
        }

        prod.setFileName(newfileName);
        Products pros = productRepo.save(prod);
        if(pros!=null){
        return new ResponseEntity<Message>(new Message(""), HttpStatus.OK);
        }else{
            return new ResponseEntity<Message>(new Message("Product Tidak tersimpan"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/Imgproducts/{id}")
    public byte[] getPhoto(@PathVariable("id")Long id)throws Exception{
        Products products = productService.findById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess")+products.getFileName()));
    }
}
