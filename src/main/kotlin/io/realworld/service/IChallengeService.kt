package io.realworld.service

import io.realworld.model.Challenge
import io.realworld.model.dto.ChallengeCreationDTO
import io.realworld.model.dto.ChallengeUpdateDTO

interface IChallengeService {
    fun getChallenges(page: Int, size: Int): List<Challenge>
    fun getChallenge(id: Long): Challenge?
    fun createChallenge(challenge: ChallengeCreationDTO): Challenge
    fun updateChallenge(challengeId: Long, challenge: ChallengeUpdateDTO): Challenge?
    fun deleteChallenge(challengeId: Long): Challenge?
    fun getUserChallenges(userId: Int, challengesId: Set<Int>): Any
    fun participateInChallenge(challengeId: Int, userId: Int): Any
}