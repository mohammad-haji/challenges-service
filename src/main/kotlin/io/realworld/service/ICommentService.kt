package io.realworld.service

import io.realworld.model.Comment
import io.realworld.model.dto.CommentCreationDTO
import io.realworld.model.dto.CommentUpdateDTO

interface ICommentService {
    fun getCommentList(page: Int, size: Int): List<Comment>
    fun getComment(id: Long): Comment?
    fun createComment(comment: CommentCreationDTO): Comment?
    fun updateComment(id: Long, comment: CommentUpdateDTO): Comment?
    fun deleteComment(id: Long): Comment?
}