package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import io.realworld.model.Comment
import java.time.LocalDateTime

@JsonRootName("items")
data class CommentDTO(
        var id: Long,
        var title: String = "",
        var description: String = "",
        var userId: Int,
        var challengeId: Int,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime
) {
    companion object {
        fun fromModel(model: Comment): CommentDTO {
            return CommentDTO(id = model.id,title = model.title, description = model.description, userId = model.userId,
                    challengeId = model.challengeId, createdAt = model.createdAt, updatedAt = model.updatedAt)
        }
    }
}