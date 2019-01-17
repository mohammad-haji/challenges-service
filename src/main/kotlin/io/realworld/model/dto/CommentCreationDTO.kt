package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import org.jetbrains.annotations.NotNull

@JsonRootName("comment")
data class CommentCreationDTO(
        var title: String = "",
        var description: String = "",
        @NotNull("userId is required")
        var userId: Int = 0,
        @NotNull("challengeId is required")
        var challengeId: Int = 0
) {

}