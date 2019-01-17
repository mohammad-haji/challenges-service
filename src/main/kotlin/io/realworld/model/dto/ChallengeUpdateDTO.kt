package io.realworld.model.dto

import com.fasterxml.jackson.annotation.JsonRootName
import org.jetbrains.annotations.NotNull

@JsonRootName("challenge")
data class ChallengeUpdateDTO(@NotNull("title is required")
                           var title: String = "",
                              var bio: String = "")