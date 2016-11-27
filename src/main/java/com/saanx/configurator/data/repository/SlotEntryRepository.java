package com.saanx.configurator.data.repository;


import com.saanx.configurator.data.entity.SlotEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface SlotEntryRepository extends PagingAndSortingRepository<SlotEntry, Long> {
}
