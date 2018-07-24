package by.bogdan.springdataelasticsearch.config

import org.elasticsearch.common.settings.Settings
import org.elasticsearch.node.NodeBuilder.nodeBuilder
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.io.File

@EnableJpaRepositories(basePackages = ["by.bogdan.springdataelasticsearch.jparepository"])
@EnableElasticsearchRepositories(basePackages = ["by.bogdan.springdataelasticsearch.repo"])
@Configurable
class ElasticConfig {


    @Bean
    fun elasticsearchOperations(): ElasticsearchOperations {
        val tempFile = File.createTempFile("elastic", System.nanoTime().toString())

        val elasticsearchSettings = Settings.builder()
                .put("http.enabled", "true")
                .put("index.number_of_shards", "1")
                .put("path.data", File(tempFile, "data").absolutePath)
                .put("path.logs", File(tempFile, "logs").absolutePath)
                .put("path.work", File(tempFile, "work").absolutePath)
                .put("path.home", tempFile)

        return ElasticsearchTemplate(nodeBuilder()
                .local(true)
                .settings(elasticsearchSettings.build())
                .node()
                .client())
    }
}