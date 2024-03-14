package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
		SaleDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleDTO>> getReport(@RequestParam(name = "minDate", defaultValue = "") String min, @RequestParam(name = "maxDate", defaultValue = "") String max, @RequestParam(name = "name", defaultValue = "") String name,
			Pageable pageable) {
		Page<SaleDTO> result = service.searchReports(min, max, name, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SaleMinDTO>> getSummary(@RequestParam(name = "minDate", defaultValue = "") String min, @RequestParam(name = "maxDate", defaultValue = "") String max, Pageable pageable) {
		Page<SaleMinDTO> result = service.searchSummary(min, max, pageable);
		return ResponseEntity.ok(result);
	}
}
