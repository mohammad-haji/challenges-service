package io.realworld.model

import io.realworld.enums.ChallengeTypes
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "challenges")
data class Challenge(
        @Column(columnDefinition = "text", nullable = false)
        var title: String = "",
        @Column(columnDefinition = "text")
        var bio: String = "",
        @Column(columnDefinition = "text")
        var description: String = "",
        @Column(columnDefinition = "text")
        var type: ChallengeTypes = ChallengeTypes.ALTITUDE,
        @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = true, updatable = false, nullable = false)
        @CreationTimestamp()
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "updatedat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = true, nullable = false)
        @UpdateTimestamp()
        var updatedAt: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
) {}