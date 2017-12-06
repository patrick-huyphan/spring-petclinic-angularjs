/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Company;
import org.springframework.samples.petclinic.model.Comment;
import org.springframework.samples.petclinic.model.Visit;

import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author patrick_huy
 */
@RestController
public class CommentResource extends AbstractResourceController{

    private final ClinicService clinicService;
    
    @Autowired
    public CommentResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }    
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
//    
//    @PostMapping("/owners/{ownerId}/companies/{petId}/comments")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void create(
//            @Valid @RequestBody Comment visit,
//            @PathVariable("ownerId") int ownId,
//            @PathVariable("petId") int petId) {
//
//        clinicService.findComByID(petId).addComments(visit, clinicService.findOwnerById(ownId));
//        clinicService.saveComment(visit);
//    }

    @GetMapping("/owners/{ownerId}/comments")
    public Object getbyOwner(@PathVariable("petId") int petId) {
        return clinicService.findAllCommentByOwner(clinicService.findOwnerById(petId));
    }
    
    
    @GetMapping("/companies/{ownerId}/comments")
    public Object getByCompanies(@PathVariable("ownerId") int Id) {
        return clinicService.findAllCommentByCompanies(clinicService.findComByID(Id));
    }
}
