package io.realworld.model.dto

import io.realworld.model.News

data class NewsDTO(var title: String, var id: Long) {
    companion object {
        fun fromModel(model: News): NewsDTO {
            return NewsDTO(model.title, model.id)
        }

    }
}