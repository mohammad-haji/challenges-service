package io.realworld.controller

import io.realworld.exception.NotFoundException
import io.realworld.model.News
import io.realworld.model.dto.NewsCreationDTO
import io.realworld.model.dto.NewsUpdateDTO
import io.realworld.service.impl.NewsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import io.realworld.model.dto.NewsDTO as NewsIO

@RestController
@RequestMapping("/api/v1/")
class NewsController() {
    @Autowired
    lateinit var newsService: NewsServiceImpl

    @GetMapping("news")
    fun getNewsList(@RequestParam(defaultValue = "0") page: Int,
                    @RequestParam(defaultValue = "20") size: Int): Any {
        return newsListView(newsService.getNewsList(page, size))
    }

    @GetMapping("news/{newsId}")
    fun getNews(@PathVariable newsId: String): Any {
        newsService.getNews(newsId.toLong())?.let {
            return newsView(it)
        }
        throw NotFoundException()
    }

    @PostMapping("news")
    fun createNews(@RequestBody news: NewsCreationDTO): Any {
        return newsView(newsService.createNews(news))
    }

    @PutMapping("news/{newsId}")
    fun updateNews(@PathVariable newsId: String, @RequestBody news: NewsUpdateDTO):Any {
        newsService.updateNews(newsId.toLong(), news)?.let {
            return newsView(it)
        }
        throw NotFoundException()
    }

    @DeleteMapping("news/{newsId}")
    fun deleteNews(@PathVariable newsId: String): Any {
        newsService.deleteNews(newsId.toLong())?.let {
            return newsView(it)
        }
        throw NotFoundException()
    }


    fun newsView(news: News) =
            mapOf("item" to NewsIO.fromModel(news),
                    "status" to 200,
                    "message" to "OK")

    fun newsListView(newsList: List<News>) =
            mapOf("items" to newsList.map { NewsIO.fromModel(it) },
                    "status" to 200,
                    "message" to "OK")

}