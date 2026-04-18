package com.cauanlagrotta.order_platform_challange.controller;

import com.cauanlagrotta.order_platform_challange.dto.ListResponse;
import com.cauanlagrotta.order_platform_challange.dto.PaginationResponse;
import com.cauanlagrotta.order_platform_challange.dto.ProductRequestDTO;
import com.cauanlagrotta.order_platform_challange.dto.ProductResponseDTO;
import com.cauanlagrotta.order_platform_challange.services.ProductService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody ProductRequestDTO dto){
    productService.create(dto);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<ListResponse<ProductResponseDTO>> list(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

    var pageResponse = productService.findAll(PageRequest.of(page, pageSize));
    return ResponseEntity.ok(new ListResponse<>(
            Map.of("totalProducts", pageResponse.getTotalElements()),
            pageResponse.getContent(),
            PaginationResponse.fromPage(pageResponse)
    ));
  }
}
