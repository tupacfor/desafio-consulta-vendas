package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	@Query(value = "SELECT obj"
			+ " FROM Sale obj"
			+ " WHERE UPPER(obj.seller.name) LIKE CONCAT('%', UPPER(:name), '%')"
			+ " AND obj.date BETWEEN :min AND :max")
	Page<Sale> searchReports(LocalDate min, LocalDate max, String name, Pageable pageable);
	
	@Query(nativeQuery = true , value = "SELECT TB_SELLER.NAME, SUM(AMOUNT) AS total "
			+ "FROM TB_SALES "
			+ "INNER JOIN TB_SELLER ON  TB_SALES.SELLER_ID = TB_SELLER.ID "
			+ "WHERE TB_SALES.DATE BETWEEN :min AND :max "
			+ "GROUP BY TB_SELLER.NAME")
	Page<SummaryProjection> searchSummary(LocalDate min, LocalDate max, Pageable pageable);
}
