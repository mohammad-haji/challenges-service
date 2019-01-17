package io.realworld.service.impl

import io.realworld.model.Challenge
import io.realworld.model.UserChallengesAssignment
import io.realworld.model.dto.ChallengeCreationDTO
import io.realworld.model.dto.ChallengeUpdateDTO
import io.realworld.repository.ChallengeRepository
import io.realworld.repository.UserChallengesAssignmentRepository
import io.realworld.service.IChallengeService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityManager

@Service
class ChallengeServiceImpl(var challengeRepository: ChallengeRepository,
                           var userChallengesAssignmentRepository: UserChallengesAssignmentRepository) : IChallengeService {
    override fun getChallenges(page: Int, size: Int): List<Challenge> {
        val p = PageRequest.of(page, size)
        return challengeRepository.findAll(p).toList()
    }

    override fun getChallenge(id: Long): Challenge? {
        return challengeRepository.findChallengeById(id)
    }

    override fun createChallenge(challenge: ChallengeCreationDTO): Challenge {
        val challenge = Challenge(challenge.title, challenge.bio, createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        return challengeRepository.save(challenge)
    }

    override fun updateChallenge(challengeId: Long, challenge: ChallengeUpdateDTO): Challenge? {
        return challengeRepository.findChallengeById(challengeId)?.let {
            val challenge = it.copy(
                    title = challenge.title ?: it.title,
                    bio = challenge.bio ?: it.bio,
                    updatedAt = LocalDateTime.now()
            )
            challengeRepository.save(challenge)
            return challenge
        }
    }

    override fun deleteChallenge(challengeId: Long): Challenge? {
        return challengeRepository.findChallengeById(challengeId)?.let {
            challengeRepository.delete(it)
            return it
        }
    }

    override fun getUserChallenges(userId: Int, challengesId: Set<Int>): List<UserChallengesAssignment> {
        return userChallengesAssignmentRepository.getUserChallengesAssignmentByUserIdAndChallengeIdIn(userId, challengesId).toList()
    }

    override fun participateInChallenge(challengeId: Int, userId: Int): UserChallengesAssignment {
        try {
            val userchallenge = userChallengesAssignmentRepository.getUserChallengesAssignmentByUserIdAndChallengeId(userId, challengeId)
            println(userchallenge)
            return userchallenge
        } catch (e: EmptyResultDataAccessException) {
            val userChallenge = UserChallengesAssignment(challengeId = challengeId, userId = userId)
            userChallengesAssignmentRepository.save(userChallenge)
            return userChallenge
        }
    }

}