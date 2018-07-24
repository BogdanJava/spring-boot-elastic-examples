package by.bogdan.springdataelasticsearch.resource

import by.bogdan.springdataelasticsearch.builder.SearchQueryBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/manual/search")
class ManualSearchResource(val searchQueryBuilder: SearchQueryBuilder) {

    @GetMapping("/{text}")
    fun getAll(@PathVariable text: String) =
        searchQueryBuilder.getAll(text)

}