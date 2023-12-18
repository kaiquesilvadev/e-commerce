package com.kaiqueDev.DSCommerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaiqueDev.DSCommerce.domain.dto.converso.ProductDtoConverso;
import com.kaiqueDev.DSCommerce.domain.dto.responce.ProductDtoResponce;
import com.kaiqueDev.DSCommerce.domain.entites.Product;
import com.kaiqueDev.DSCommerce.domain.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductControlle {

	@Autowired
	private ProductService service;

	@Autowired
	ProductDtoConverso converso;

	@GetMapping
	public Page<ProductDtoResponce> lista(Pageable pageable) {
		Page<Product> pageProduct = service.lista(pageable);
		List<ProductDtoResponce> Product = converso.ListDto(pageProduct.getContent());
		return new PageImpl<>(Product, pageable, pageProduct.getTotalElements());
	}

	@GetMapping("/{id}")
	public ProductDtoResponce duscaPorId(@PathVariable Long id) {
		return converso.convertiEntiti(service.buscaPorId(id));
	}
}