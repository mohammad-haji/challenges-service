package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import org.jetbrains.annotations.NotNull

@JsonRootName("news")
data class NewsCreationDTO(
        @NotNull("title is required")
        var title: String, var id: Long)