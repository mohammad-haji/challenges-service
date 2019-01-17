package io.realworld.repository

import io.realworld.model.News
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository : PagingAndSortingRepository<News, Long> {
    fun findNewsById(id: Long): News?
}