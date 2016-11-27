package com.saanx.configurator.data.repository;


import com.saanx.configurator.data.entity.Configuration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConfigurationRepository extends PagingAndSortingRepository<Configuration, Long> {
	@Override
	Configuration findOne(Long aLong);
}
