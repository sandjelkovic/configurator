package com.saanx.configurator.data.repository;


import com.saanx.configurator.data.entity.Slot;
import com.saanx.configurator.data.projection.SlotProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = SlotProjection.class)
public interface SlotRepository extends PagingAndSortingRepository<Slot, Long> {
}
