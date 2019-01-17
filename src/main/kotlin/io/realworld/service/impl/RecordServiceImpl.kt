package io.realworld.service.impl

import io.realworld.model.Record
import io.realworld.model.dto.RecordCreationDTO
import io.realworld.model.dto.RecordUpdateDTO
import io.realworld.repository.RecordRepository
import io.realworld.service.IRecordService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class RecordServiceImpl(var recordRepository: RecordRepository) : IRecordService {
    override fun getRecordsList(page: Int, size: Int): List<Record> {
        val p = PageRequest.of(page, size)
        return recordRepository.findAll(p).toList()
    }

    override fun getRecord(id: Long): Record? {
        return recordRepository.findRecordById(id)
    }

    override fun createRecord(record: RecordCreationDTO): Record? {
        val newRecord: Record = Record(record.title, record.challengeId, record.userId)
        return recordRepository.save(newRecord)
    }

    override fun updateRecord(id: Long, record: RecordUpdateDTO): Record? {
        return recordRepository.findRecordById(id)?.let {
            val updateRecord: Record = it.copy(title = record.title ?: it.title)
            recordRepository.save(updateRecord)
            return updateRecord
        }
    }

    override fun deleteRecord(id: Long): Record? {
        return recordRepository.findRecordById(id)?.let {
            recordRepository.deleteById(id)
            return it
        }
    }
}