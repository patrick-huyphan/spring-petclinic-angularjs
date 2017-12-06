/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.FileInfo;

/**
 *
 * @author patrick_huy
 */
public interface FileInfoResporitory extends Repository<FileInfo, Integer> {
    
}
