package io.realworld.model

import org.jetbrains.annotations.NotNull
import javax.persistence.*

@Entity
@Table(name = "records")
data class Record(
        @Column(columnDefinition = "text")
        var title: String = "",
        @Column(name = "challenge_id", nullable = false)
        var challengeId: Int = 0,
        @Column(name = "user_id", nullable = false)
        var userId: Int = 0,
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(unique = true, nullable = false)
        var id: Long = 0

) {}