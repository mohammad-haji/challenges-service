package io.realworld.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity()
@Table(name = "comments")
data class Comment(
        @Column(columnDefinition = "text")
        var title: String = "",
        @Column(columnDefinition = "text")
        var description: String = "",
        @Column(nullable = false, name = "user_id")
        var userId: Int = 0,
        @Column(nullable = false, name = "challenge_id")
        var challengeId: Int = 0,
        @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = true, updatable = false, nullable = false)
        @CreationTimestamp()
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @Column(name = "updatedat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = true, nullable = false)
        @UpdateTimestamp()
        var updatedAt: LocalDateTime = LocalDateTime.now(),
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0
) {

}