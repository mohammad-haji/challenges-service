package io.realworld.service

import io.realworld.model.News
import io.realworld.model.dto.NewsCreationDTO
import io.realworld.model.dto.NewsUpdateDTO

interface INewsService {
    fun getNewsList(page: Int, size: Int): List<News>
    fun getNews(id: Long): News?
    fun createNews(news: NewsCreationDTO): News
    fun updateNews(id: Long, news: NewsUpdateDTO): News?
    fun deleteNews(id: Long): News?
}