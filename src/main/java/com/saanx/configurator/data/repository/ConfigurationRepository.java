package com.saanx.configurator.data.repository;


import com.saanx.configurator.data.entity.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface ConfigurationRepository extends PagingAndSortingRepository<Configuration, Long> {
	@Override
	@PreAuthorize("#entity?.id == null or @configurationRepository.findOne(#entity?.id)?.user.username == authentication.name")
	Configuration save(@Param("entity") Configuration entity);

	@Override
	@PostAuthorize("returnObject?.user?.username == authentication.name and returnObject?.public")
	Configuration findOne(Long id);

	@Override
	@PreAuthorize("@configurationRepository.findOne(#id)?.user.username == authentication.name")
	void delete(Long id);

	@Override
	@PreAuthorize("@configurationRepository.findOne(#entity?.id)?.user.username == authentication.name")
	void delete(Configuration entity);

	@PostAuthorize("#username == authentication.name")
	@RestResource(path = "configurationss", rel = "configurationss")
	Page<Configuration> findByUser_Username(String username, Pageable pageable);

	@Override
	@RestResource(exported = false)
	Iterable<Configuration> findAll();

	@Override
	@RestResource(exported = false)
	Iterable<Configuration> findAll(Sort sort);

	@Override
	@PreAuthorize("hasRole('ROOT')")
	Page<Configuration> findAll(Pageable pageable);

	@Override
	@RestResource(exported = false)
	Iterable<Configuration> findAll(Iterable<Long> ids);

	@Override
	@RestResource(exported = false)
	<S extends Configuration> Iterable<S> save(Iterable<S> entities);

	@Override
	@RestResource(exported = false)
	boolean exists(Long id);

	@Override
	@RestResource(exported = false)
	long count();

	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends Configuration> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();
}
