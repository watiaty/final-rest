package by.epam.shop.restcontroller;

import by.epam.shop.dto.ProductDTO;
import by.epam.shop.dto.ProductDTOToProduct;
import by.epam.shop.dto.ProductToProductDTO;
import by.epam.shop.entity.Product;
import by.epam.shop.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("/api/products")
public class ProductController {
    private final ProductServiceImpl productService;
    private final ProductToProductDTO productToProductDTO;
    private final ProductDTOToProduct productDTOToProduct;

    public ProductController(ProductServiceImpl productService, ProductToProductDTO productToProductDTO, ProductDTOToProduct productDTOToProduct) {
        this.productService = productService;
        this.productToProductDTO = productToProductDTO;
        this.productDTOToProduct = productDTOToProduct;
    }

    // Read list of products
    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('products:read')")
    public ResponseEntity<List<ProductDTO>> findAll(@RequestParam(name = "page") Integer page) {

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : productService.findAll(page)) {
            productDTOS.add(productToProductDTO.convert(product));
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    // Search by id
    @PreAuthorize("hasAnyAuthority('products:read')")
    @GetMapping("id/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productToProductDTO.convert(product), HttpStatus.OK);
    }

    // Search by name
    @PreAuthorize("hasAnyAuthority('products:read')")
    @GetMapping("name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByName(@RequestParam(name = "page") Integer page, @PathVariable String name) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : productService.findByName(page, name)) {
            productDTOS.add(productToProductDTO.convert(product));
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    // Search by category id
    @PreAuthorize("hasAnyAuthority('products:read')")
    @GetMapping("category/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@RequestParam(name = "page") Integer page, @PathVariable Integer id) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : productService.findByCategoryId(page, id)) {
            productDTOS.add(productToProductDTO.convert(product));
        }
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

//    // Search by price
//    @GetMapping("price/{price}")
//    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Integer price) {
//        List<ProductDTO> productDTOS = new ArrayList<>();
//        for (Product product : productService.findByCategoryId(id)) {
//            productDTOS.add(productToProductDTO.convert(product));
//        }
//        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
//    }

    // Create new product
    @PreAuthorize("hasAnyAuthority('products:write')")
    @PostMapping("create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.save(productDTOToProduct.convert(productDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update product by id
    @PreAuthorize("hasAnyAuthority('products:write')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
        productDTO.setId(id);
        productService.save(productDTOToProduct.convert(productDTO));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // Delete product by id
    @PreAuthorize("hasAnyAuthority('products:write')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Integer id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
