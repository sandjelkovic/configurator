package com.saanx.configurator.data.repository;


import com.saanx.configurator.data.entity.Slot;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface SlotRepository extends PagingAndSortingRepository<Slot, Long> {
}
