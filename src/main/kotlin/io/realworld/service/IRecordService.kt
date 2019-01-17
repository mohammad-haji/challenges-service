package io.realworld.service

import io.realworld.model.Record
import io.realworld.model.dto.RecordCreationDTO
import io.realworld.model.dto.RecordUpdateDTO

interface IRecordService {
    fun getRecordsList(page: Int, size: Int): List<Record>
    fun getRecord(id: Long): Record?
    fun createRecord(record: RecordCreationDTO): Record?
    fun updateRecord(id: Long, record: RecordUpdateDTO): Record?
    fun deleteRecord(id: Long): Record?
}