package io.realworld.model.dto

import io.realworld.model.Record

data class RecordDTO(var title: String, var chalengeId: Int, var userId: Int, var id: Long) {
    companion object {
        fun fromModel(model: Record): RecordDTO {
            return RecordDTO(model.title, model.challengeId, model.userId, model.id)
        }
    }
}