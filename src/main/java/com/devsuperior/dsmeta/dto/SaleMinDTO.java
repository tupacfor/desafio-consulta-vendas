package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;

public class SaleMinDTO {

	private String sellerName;
	private Double total;
	
	public SaleMinDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	public SaleMinDTO(Sale entity) {
		sellerName = entity.getSeller().getName();
		total = entity.getAmount();
	}
	
	public SaleMinDTO(SummaryProjection entity) {
		sellerName = entity.getName();
		total = entity.getTotal();
	}

	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}

	

	
}