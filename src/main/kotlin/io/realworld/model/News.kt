package io.realworld.model

import javax.persistence.*

@Entity
@Table(name = "news")
data class News(
        @Column(columnDefinition = "text")
        var title: String = "",
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0) {}