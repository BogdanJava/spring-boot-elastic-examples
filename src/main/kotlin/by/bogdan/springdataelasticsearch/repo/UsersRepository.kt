package by.bogdan.springdataelasticsearch.repo

import by.bogdan.springdataelasticsearch.model.Users
import by.bogdan.springdataelasticsearch.model.UsersEntity
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface UsersRepository : ElasticsearchRepository<Users, Long> {
    fun findByName(text: String): List<Users>
    fun findBySalary(text: String): List<Users>
}