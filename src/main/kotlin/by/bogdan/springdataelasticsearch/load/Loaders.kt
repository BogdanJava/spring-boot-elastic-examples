package by.bogdan.springdataelasticsearch.load

import by.bogdan.springdataelasticsearch.jparepository.UsersJpaRepository
import by.bogdan.springdataelasticsearch.model.Users
import by.bogdan.springdataelasticsearch.repo.UsersRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Component
class Loaders(val usersRepository: UsersRepository,
              val operations: ElasticsearchOperations,
              val usersJpaRepository: UsersJpaRepository) {

    private val log: Logger = LoggerFactory.getLogger(Loaders::class.java)

    @Transactional
    @PostConstruct
    fun init() {
        operations.putMapping(Users::class.java)
        log.info("Loading data")
        val data = getData().map { users -> users.toUsersEntity() }
        usersJpaRepository.save(data)
        val all = usersJpaRepository.findAll().map { users -> users.toUsers() }
        usersRepository.save(all)
        log.info("Loading completed")
    }

    private fun getData() = listOf(
            Users(name = "Bogdan", id = 1L, teamName = "Development", salary = 500L),
            Users(name = "Stefa", id = 2L, teamName = "Designing", salary = 500L),
            Users(name = "Stas", id = 3L, teamName = "Marketing", salary = 500L)
    )

}