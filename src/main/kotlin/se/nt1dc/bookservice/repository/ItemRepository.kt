package se.nt1dc.bookservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import se.nt1dc.bookservice.entity.Item

interface ItemRepository : JpaRepository<Item, Int> {
}