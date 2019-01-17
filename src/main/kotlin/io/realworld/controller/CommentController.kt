package io.realworld.controller

import io.realworld.exception.NotFoundException
import io.realworld.model.Comment
import io.realworld.model.dto.CommentCreationDTO
import io.realworld.model.dto.CommentUpdateDTO
import io.realworld.service.impl.CommentServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import io.realworld.model.dto.CommentDTO as CommentIO


@RestController
@RequestMapping("/api/v1/")
class CommentController() {
    @Autowired
    lateinit var commentServiceImpl: CommentServiceImpl

    @GetMapping("comments")
    fun getCommentList(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "20") size: Int): Any {
        return commentListView(commentServiceImpl.getCommentList(page, size))
    }

    @GetMapping("comments/{id}")
    fun getComment(@PathVariable id: Long): Any {
        commentServiceImpl.getComment(id)?.let {
            return commentView(it)
        }
        throw NotFoundException()
    }

    @PostMapping("comments")
    fun createComment(@RequestBody comment: CommentCreationDTO): Any {
        return commentView(commentServiceImpl.createComment(comment))
    }

    @PutMapping("comments/{id}")
    fun updateComment(@PathVariable id: Long, @RequestBody comment: CommentUpdateDTO): Any {
        commentServiceImpl.updateComment(id, comment)?.let {
            return commentView(it)
        }
        throw NotFoundException()
    }

    @DeleteMapping("comments/{id}")
    fun deleteComment(@PathVariable id: Long): Any {
        commentServiceImpl.deleteComment(id)?.let {
            return commentView(it)
        }
        throw NotFoundException()
    }


    fun commentView(comment: Comment) =
            mapOf("item" to CommentIO.fromModel(comment),
                    "status" to 200,
                    "message" to "OK")

    fun commentListView(commentList: List<Comment>) =
            mapOf("items" to commentList.map { CommentIO.fromModel(it) },
                    "status" to 200,
                    "message" to "OK")
}