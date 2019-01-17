package io.realworld.repository

import io.realworld.model.Record
import org.springframework.data.repository.PagingAndSortingRepository

interface RecordRepository : PagingAndSortingRepository<Record, Long> {
    fun findRecordById(id: Long): Record?
}