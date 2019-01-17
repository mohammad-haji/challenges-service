package io.realworld.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonRootName
import javax.persistence.*

@Entity
@Table(name = "users")
@JsonRootName("user")
data class User(
                @Column(columnDefinition = "text")
                var email: String = "",
                @Column(columnDefinition = "text")
                @JsonIgnore
                var password: String = "",
                @Column(columnDefinition = "text")
                var token: String = "",
                @Column(columnDefinition = "text")
                var username: String = "",
                @Column(columnDefinition = "text")
                var bio: String = "",
                @Column(columnDefinition = "text")
                var image: String = "",
                @ManyToMany
                @JsonIgnore
                var follows: MutableList<User> = mutableListOf(),
                @Id @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long = 0) {
    override fun toString(): String = "User($email, $username)"
}