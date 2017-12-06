/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 *
 * @author patrick_huy
 */
@Entity
@Table(name = "companies")
public class Company extends NamedEntity{
    @Column(name = "address")
    private String address;

    @Column(name = "taxcode")
    private String taxCode;
    
    @Column(name = "info")
    private String info;
    
    @Column(name = "telephone")
    private String telephone;


//    @OneToMany(mappedBy = "companies", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Column(nullable = true)
//    @JsonManagedReference
//    private Set<Comment> comments;
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

//    protected void setCommentsInternal(Set<Comment> pets) {
//        this.comments = pets;
//    }
//
//    protected Set<Comment> getCommentsInternal() {
//        if (this.comments == null) {
//            this.comments = new HashSet<>();
//        }
//        return this.comments;
//    }
//
//    public List<Comment> getComments() {
//        List<Comment> sortedPets = new ArrayList<>(getCommentsInternal());
//        PropertyComparator.sort(sortedPets, new MutableSortDefinition("com_id", true, true));
//        return Collections.unmodifiableList(sortedPets);
//    }
//
//    public void addComments(Comment pet, Owner owner) {
//        getCommentsInternal().add(pet);
//        pet.setOwner(owner);
//    }
//    

    @Override
    public String toString() {
        return this.getName()+" "+ this.getAddress()+" "+this.getTelephone()+" "+ this.getTaxCode()+" "+ this.getInfo();
    }
}
