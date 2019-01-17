package io.realworld.model.dto

import io.realworld.model.UserChallengesAssignment

data class UserChallengesAssignmentDTO(
        var id: Long,
        var challengeId: Int
) {
    companion object {
        fun fromModel(model: UserChallengesAssignment): UserChallengesAssignmentDTO {
            return UserChallengesAssignmentDTO(challengeId = model.challengeId, id = model.id)
        }
    }
}