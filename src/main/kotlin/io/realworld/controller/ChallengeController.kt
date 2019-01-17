package io.realworld.controller

import io.realworld.exception.NotFoundException
import io.realworld.jwt.ApiKeySecured
import io.realworld.model.Challenge
import io.realworld.model.UserChallengesAssignment
import io.realworld.model.dto.ChallengeCreationDTO
import io.realworld.model.dto.ChallengeUpdateDTO
import io.realworld.model.dto.UserChallengesBodyDTO
import io.realworld.service.UserService
import io.realworld.service.impl.ChallengeServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import io.realworld.model.dto.ChallengeDTO as ChallengeIO
import io.realworld.model.dto.UserChallengesAssignmentDTO as UserChallengesAssignmentIO

@RestController
@RequestMapping("/api/v1/")
class ChallengeController() {

    @Autowired
    lateinit var challengeService: ChallengeServiceImpl
    @Autowired
    lateinit var userService: UserService

    @GetMapping("challenges")
    fun challenges(@RequestParam(defaultValue = "20") size: Int,
                   @RequestParam(defaultValue = "0") page: Int): Any {
        return challengesView(challengeService.getChallenges(page, size))
    }

    @GetMapping("challenges/{challengeId}")
    fun challenge(@PathVariable challengeId: String): Any {
        challengeService.getChallenge(challengeId.toLong())?.let {
            return challengeView(it)
        }
        throw NotFoundException()
    }

    @PostMapping("challenges")
    fun newChallenge(@RequestBody challengeCreationDTO: ChallengeCreationDTO): Any {
        return challengeView(challengeService.createChallenge(challengeCreationDTO))
    }

    @PutMapping("challenges/{challengeId}")
    fun updateChallenge(@PathVariable challengeId: String, @RequestBody challenge: ChallengeUpdateDTO): Any {
        challengeService.updateChallenge(challengeId.toLong(), challenge)?.let {
            return challengeView(it)
        }
        throw NotFoundException()
    }

    @DeleteMapping("challenges/{challengeId}")
    fun deleteChallenge(@PathVariable challengeId: String): Any {
        challengeService.deleteChallenge(challengeId.toLong())?.let {
            return challengeView(it)
        }
        throw NotFoundException()
    }

    @ApiKeySecured
    @PostMapping("challenges/user")
    fun userChallenges(@RequestBody participateBody: UserChallengesBodyDTO): Any {
        val user = userService.currentUser()
        return userChallengesListView(challengeService.getUserChallenges(user.id.toInt(), participateBody.ids))
    }

    @ApiKeySecured
    @PostMapping("challenges/{challengeId}/participate")
    fun participate(@PathVariable challengeId: Int): Any {
        val user = userService.currentUser()
        return userChallengeView(challengeService.participateInChallenge(challengeId = challengeId, userId = user.id.toInt()))
    }

    fun challengeView(challenge: Challenge) =
            mapOf("item" to ChallengeIO.fromModel(challenge),
                    "status" to 200,
                    "message" to "OK")

    fun userChallengesListView(userChallenges: List<UserChallengesAssignment>) =
            mapOf("items" to userChallenges.map { UserChallengesAssignmentIO.fromModel(it) },
                    "status" to 200,
                    "message" to "OK")

    fun userChallengeView(userChallengesAssignment: UserChallengesAssignment) =
            mapOf("item" to UserChallengesAssignmentIO.fromModel(userChallengesAssignment),
                    "status" to 200,
                    "message" to "OK")

    fun challengesView(challenges: List<Challenge>) =
            mapOf("items" to challenges.map { ChallengeIO.fromModel(it) },
                    "status" to 200,
                    "message" to "OK")


    companion object {
        private val LOG = LoggerFactory.getLogger(ChallengeController::class.java)
    }

}