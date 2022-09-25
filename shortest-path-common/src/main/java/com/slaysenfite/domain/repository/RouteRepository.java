package com.slaysenfite.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.slaysenfite.domain.entity.Route;

import javax.transaction.Transactional;

/**
 * This RouteRepository interface extends JpaRepository to provide basic CRUD operations for Planet objects
 */
@Transactional
@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

}
