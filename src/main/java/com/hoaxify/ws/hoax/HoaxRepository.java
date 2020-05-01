package com.hoaxify.ws.hoax;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Yasin Memic on Apr, 2020
 */
public interface HoaxRepository extends JpaRepository<Hoax, Long> {

    Page<Hoax> findAllByUser_Username(String username, Pageable page);
}
