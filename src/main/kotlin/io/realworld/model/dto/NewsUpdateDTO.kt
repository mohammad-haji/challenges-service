package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("news")
data class NewsUpdateDTO(
        var title: String,
        var id: Long
)