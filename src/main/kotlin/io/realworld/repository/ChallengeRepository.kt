package io.realworld.repository

import io.realworld.model.Challenge
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChallengeRepository : PagingAndSortingRepository<Challenge, Long> {
    fun findChallengeById(id: Long): Challenge?
}