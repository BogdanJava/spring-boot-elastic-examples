package by.bogdan.springdataelasticsearch.model

import org.springframework.data.elasticsearch.annotations.Document
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Document(indexName = "users", type = "users", shards = 1)
data class Users(var name: String,
                 var id: Long,
                 var teamName: String,
                 var salary: Long) {
    constructor() : this("", 0, "", 0)

    fun toUsersEntity() = UsersEntity(name, id, teamName, salary)
}

@Entity
data class UsersEntity(var name: String,
                       @Id @GeneratedValue var id: Long,
                       @Column(name = "team_name") var teamName: String,
                       var salary: Long) {
    constructor() : this("", 0, "", 0)

    fun toUsers() = Users(name, id, teamName, salary)
}