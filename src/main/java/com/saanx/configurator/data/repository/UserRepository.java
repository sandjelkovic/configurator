package com.saanx.configurator.data.repository;


import com.saanx.configurator.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	List<User> findByEmail(String email);

	Optional<User> findOneByUsername(String username);
}
