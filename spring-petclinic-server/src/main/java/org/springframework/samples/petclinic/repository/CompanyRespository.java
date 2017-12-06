/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.repository;


import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Company;

/**
 *
 * @author patrick_huy
 */
public interface CompanyRespository extends JpaRepository<Company, Integer>{
    Company findById(int id);
//    void save(Company pet);
}
