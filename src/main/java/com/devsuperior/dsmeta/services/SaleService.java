package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleDTO(entity);
	}
	
	public Page<SaleDTO> searchReports(String minDate, String maxDate, String name, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate past = max.minusYears(1);
		LocalDate min = minDate.equals("") ? past : LocalDate.parse(minDate);
				
		
		Page<Sale> result = repository.searchReports(min, max, name, pageable);
		return result.map(x -> new SaleDTO(x));
	}

public Page<SaleMinDTO> searchSummary(String minDate, String maxDate, Pageable pageable) {
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		LocalDate past = max.minusYears(1);
		LocalDate min = minDate.equals("") ? past : LocalDate.parse(minDate);
				
		
		Page<SummaryProjection> result = repository.searchSummary(min, max, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}
	
}
