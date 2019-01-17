package io.realworld.model

import javax.persistence.*

@Entity
@Table(name = "user_challenges_assignment")
data class UserChallengesAssignment(
        @Column(name = "user_id")
        var userId: Int = 0,
        @Column(name = "challenge_id")
        var challengeId: Int = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
) {

}