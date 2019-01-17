package io.realworld.repository

import io.realworld.model.Comment
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : PagingAndSortingRepository<Comment, Long> {
    fun findCommentById(id: Long): Comment?
}