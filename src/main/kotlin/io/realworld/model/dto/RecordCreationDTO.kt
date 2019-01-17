package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import org.jetbrains.annotations.NotNull

@JsonRootName("record")
data class RecordCreationDTO(
        var title: String = "",
        @NotNull("challengeId is required")
        var challengeId: Int,
        @NotNull("userId is required")
        var userId: Int
) {

}