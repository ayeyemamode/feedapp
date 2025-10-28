package com.bptn.feedapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeedRepository extends JpaRepository<T, ID>, PagingAndSortingRepository<T, ID> {

}
