package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("record")
data class RecordUpdateDTO(var title: String) {}