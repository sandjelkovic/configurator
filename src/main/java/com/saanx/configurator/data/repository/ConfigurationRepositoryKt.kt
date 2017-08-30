package com.saanx.configurator.data.repository

import com.saanx.configurator.data.entity.ConfigurationKt
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize

/**
 * @author sandjelkovic
 * @date 6.6.17.
 */
@RepositoryRestResource
interface ConfigurationRepositoryKt : PagingAndSortingRepository<ConfigurationKt, Long> {
    @PreAuthorize("#entity?.id == null or @configurationRepository.findOne(#entity?.id)?.user?.username == authentication.name")
    override fun <S : ConfigurationKt?> save(@Param("entity") entity: S): S

    @PostAuthorize("returnObject == null or returnObject?.user?.username?.equals(authentication.name) or returnObject?.public")
    override fun findOne(id: Long?): ConfigurationKt

    @PreAuthorize("@configurationRepository.findOne(#id)?.user.username == authentication.name")
    override fun delete(id: Long?)

    @PreAuthorize("@configurationRepository.findOne(#entity?.id)?.user.username == authentication.name")
    override fun delete(entity: ConfigurationKt)

    @PostAuthorize("#username == authentication.name")
    @RestResource(path = "owned", rel = "owned")
    fun findByUser_Username(username: String, pageable: Pageable): Page<ConfigurationKt>
//
//	@PostAuthorize("#username == authentication.name")
//	@RestResource(path = "owned", rel = "owned")
//	Page<Configuration> findByUser_Username(String username, Pageable pageable);

    @RestResource(exported = false)
    override fun findAll(): Iterable<ConfigurationKt>

    @RestResource(exported = false)
    override fun findAll(sort: Sort): Iterable<ConfigurationKt>

    @PreAuthorize("hasRole('ROOT')")
    override fun findAll(pageable: Pageable): Page<ConfigurationKt>

    @RestResource(exported = false)
    override fun findAll(ids: Iterable<Long>): Iterable<ConfigurationKt>

    @RestResource(exported = false)
    override fun <S : ConfigurationKt> save(entities: Iterable<S>): Iterable<S>

    @RestResource(exported = false)
    override fun exists(id: Long?): Boolean

    @RestResource(exported = false)
    override fun count(): Long

    @RestResource(exported = false)
    override fun delete(entities: Iterable<ConfigurationKt>)

    @RestResource(exported = false)
    override fun deleteAll()
}
