package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("challenge")
data class UserChallengesBodyDTO(
        var ids: Set<Int>
) {}