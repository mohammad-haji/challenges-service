package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import java.time.LocalDateTime

@JsonRootName("items")
data class ChallengeDTO(
        var title: String,
        var id: Long,
        var bio: String,
        var createdat: LocalDateTime,
        var updatedat: LocalDateTime
) {
    companion object {
        fun fromModel(model: io.realworld.model.Challenge): ChallengeDTO {
            return ChallengeDTO(title = model.title, id = model.id, bio = model.bio,
                    createdat = model.createdAt, updatedat = model.updatedAt)
        }
    }
}
