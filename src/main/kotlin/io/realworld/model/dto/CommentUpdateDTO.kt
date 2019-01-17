package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import org.jetbrains.annotations.NotNull

@JsonRootName("comment")
data class CommentUpdateDTO(
        var title: String = "",
        var description: String = ""
) {

}