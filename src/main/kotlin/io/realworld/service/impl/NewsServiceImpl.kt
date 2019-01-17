package io.realworld.service.impl

import io.realworld.model.News
import io.realworld.model.dto.NewsCreationDTO
import io.realworld.model.dto.NewsUpdateDTO
import io.realworld.repository.NewsRepository
import io.realworld.service.INewsService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class NewsServiceImpl(var newsRepository: NewsRepository) : INewsService {
    override fun getNewsList(page: Int, size: Int): List<News> {
        val p = PageRequest.of(page, size)
        return newsRepository.findAll(p).toList()
    }

    override fun getNews(id: Long): News? {
        return newsRepository.findNewsById(id)
    }

    override fun createNews(news: NewsCreationDTO): News {
        val news: News = News(news.title, news.id)
        return newsRepository.save(news)
    }

    override fun updateNews(id: Long, news: NewsUpdateDTO): News? {
        return newsRepository.findNewsById(id)?.let {
            val news = it.copy(title = news.title ?: it.title)
            newsRepository.save(news)
            return news
        }
    }

    override fun deleteNews(id: Long): News? {
        return newsRepository.findNewsById(id)?.let {
            newsRepository.deleteById(id)
            return it
        }
    }
}
