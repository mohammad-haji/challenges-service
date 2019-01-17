package io.realworld.controller

import io.realworld.exception.NotFoundException
import io.realworld.model.Record
import io.realworld.model.dto.RecordCreationDTO
import io.realworld.model.dto.RecordUpdateDTO
import io.realworld.service.impl.RecordServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import io.realworld.model.dto.RecordDTO as RecordIO


@RestController
@RequestMapping("/api/v1/")
class RecordController() {
    @Autowired
    lateinit var recordServiceImpl: RecordServiceImpl

    @GetMapping("records")
    fun getRecordList(@RequestParam(defaultValue = "0") page: Int,
                      @RequestParam(defaultValue = "20") size: Int): Any {
        return recordsVeiw(recordServiceImpl.getRecordsList(page, size))
    }

    @GetMapping("records/{recordId}")
    fun getRecord(@PathVariable recordId: String): Any {
        recordServiceImpl.getRecord(recordId.toLong())?.let {
            return recordView(it)
        }
        throw NotFoundException()
    }

    @PostMapping("records")
    fun createRecord(@RequestBody record: RecordCreationDTO): Any {
        recordServiceImpl.createRecord(record)?.let {
            return recordView(it)
        }
        throw NotFoundException()
    }

    @PutMapping("records/{recordId}")
    fun updateRecord(@PathVariable recordId: String, @RequestBody record: RecordUpdateDTO): Any {
        recordServiceImpl.updateRecord(recordId.toLong(), record)?.let {
            return recordView(it)
        }
        throw NotFoundException()
    }

    @DeleteMapping("records/{recordId}")
    fun deleteRecord(@PathVariable recordId: Long): Any {
        recordServiceImpl.deleteRecord(recordId)?.let {
            return recordView(it)
        }
        throw NotFoundException()
    }


    fun recordView(record: Record) = mapOf("item" to RecordIO.fromModel(record),
            "status" to 200,
            "message" to "OK")

    fun recordsVeiw(recordList: List<Record>) = mapOf("items" to recordList.map { RecordIO.fromModel(it) },
            "status" to 200,
            "message" to "OK")
}