package se.nt1dc.bookservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import se.nt1dc.bookservice.entity.Stock

interface StockRepository : JpaRepository<Stock, Int> {
}