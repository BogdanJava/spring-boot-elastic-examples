package by.bogdan.springdataelasticsearch.resource

import by.bogdan.springdataelasticsearch.model.Users
import by.bogdan.springdataelasticsearch.repo.UsersRepository
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/rest/search")
class SearchResource(val usersRepository: UsersRepository,
                     val esTemplate: ElasticsearchTemplate) {

    @GetMapping("/name/{text}")
    fun searchName(@PathVariable text: String) = usersRepository.findByName(text)

    @GetMapping("/salary/{text}")
    fun searchSalary(@PathVariable text: String) = usersRepository.findBySalary(text)

    @GetMapping("/all")
    fun searchAll(): List<Users> {
        val usersList = ArrayList<Users>()
        val found = usersRepository.findAll()
        found.forEach { users -> usersList.add(users) }
        return usersList
    }

    @DeleteMapping("/delete")
    fun delete(): Boolean = esTemplate.deleteIndex(Users::class.java)

}