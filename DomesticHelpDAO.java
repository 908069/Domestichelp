package com.cg.aps.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.aps.entity.DomesticHelp;
/**
 * 
 * @author Harsh
 * repository of domestic help
 *
 */
@Repository
public interface DomesticHelpDAO extends JpaRepository<DomesticHelp,Integer> {

	@Query("select d from DomesticHelp d where d.personName = :name")
	public List<DomesticHelp> findByPersonName(String name) throws Exception;
}
