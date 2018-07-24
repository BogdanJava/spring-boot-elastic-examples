package by.bogdan.springdataelasticsearch.builder

import by.bogdan.springdataelasticsearch.model.Users
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Component

@Component
class SearchQueryBuilder(val elasticsearchTemplate: ElasticsearchTemplate) {

    fun getAll(text: String): List<Users> {
        val query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("name")
                        .field("teamName")
                ).should(QueryBuilders.queryStringQuery("*$text*").lenient(true)
                        .field("name")
                        .field("teamName"))
        val queryBuilder = NativeSearchQueryBuilder()
                .withQuery(query).build()
        return elasticsearchTemplate.queryForList(queryBuilder, Users::class.java)
    }
}