package io.realworld.service.impl

import io.realworld.model.Comment
import io.realworld.model.dto.CommentCreationDTO
import io.realworld.model.dto.CommentUpdateDTO
import io.realworld.repository.CommentRepository
import io.realworld.service.ICommentService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentServiceImpl(val commentRepository: CommentRepository) : ICommentService {
    override fun getCommentList(page: Int, size: Int): List<Comment> {
        val p = PageRequest.of(page, size)
        return commentRepository.findAll(p).toList()
    }

    override fun getComment(id: Long): Comment? {
        return commentRepository.findCommentById(id)
    }

    override fun createComment(comment: CommentCreationDTO): Comment {
        val createComment = Comment(title = comment.title, description = comment.description,
                userId = comment.userId, challengeId = comment.challengeId,
                createdAt = LocalDateTime.now())
        return commentRepository.save(createComment)
    }

    override fun updateComment(id: Long, comment: CommentUpdateDTO): Comment? {
        return commentRepository.findCommentById(id)?.let {
            val updateComment = it.copy(
                    title = comment.title ?: it.title,
                    description = comment.description ?: it.description,
                    updatedAt = LocalDateTime.now()
            )
            commentRepository.save(updateComment)
            return updateComment
        }
    }

    override fun deleteComment(id: Long): Comment? {
        return commentRepository.findCommentById(id)?.let {
            commentRepository.delete(it)
            return it
        }
    }
}