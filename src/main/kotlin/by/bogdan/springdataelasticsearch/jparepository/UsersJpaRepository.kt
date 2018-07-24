package by.bogdan.springdataelasticsearch.jparepository

import by.bogdan.springdataelasticsearch.model.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsersJpaRepository : JpaRepository<UsersEntity, Long> {
}