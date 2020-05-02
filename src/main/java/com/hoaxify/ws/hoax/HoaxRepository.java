package com.hoaxify.ws.hoax;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created By Yasin Memic on Apr, 2020
 */
public interface HoaxRepository extends JpaRepository<Hoax, Long> {

    Page<Hoax> findAllByUser_Username(String username, Pageable page);

    Page<Hoax> findByIdLessThan(Long id, Pageable page);

    Page<Hoax> findAllByUser_UsernameAndIdLessThan(String username, Long id, Pageable page);

    Long countByIdGreaterThan(Long id);

    Long countByIdGreaterThanAndUser_Username(Long id, String username);

    List<Hoax> findByIdGreaterThan(Long id, Sort sort);

    List<Hoax> findByIdGreaterThanAndUser_Username(Long id, String username, Sort sort);
}
