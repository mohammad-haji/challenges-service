package io.realworld.repository

import io.realworld.model.UserChallengesAssignment
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserChallengesAssignmentRepository : PagingAndSortingRepository<UserChallengesAssignment, Long> {

    //    @Query("SELECT r.challengeId FROM UserChallengesAssignment r where r.userId=15 or r.challengeId in (:challengesId)")
//    fun getUserChallengesAssignments(@Param("challengesId") challengesId: Set<Int>): List<Any>
    fun getUserChallengesAssignmentByUserIdAndChallengeIdIn(userId: Int, challengeId: Set<Int>): List<UserChallengesAssignment>

    fun getUserChallengesAssignmentByUserIdAndChallengeId(userId: Int, challengeId: Int): UserChallengesAssignment
}