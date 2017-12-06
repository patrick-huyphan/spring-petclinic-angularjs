/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.web;

import java.util.Collection;
import static java.util.Collections.list;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.model.Company;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.samples.petclinic.model.Comment;
/**
 *
 * @author patrick_huy
 */
@RestController
public class CompanyResource extends AbstractResourceController {
    private final ClinicService clinicService;
    
    @Autowired
    public CompanyResource(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    private Company retrieveOwner(int ownerId) {
        return this.clinicService.findComByID(ownerId);
    }

    /**
     * Create Company
     */
    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createOwner(@Valid @RequestBody Company owner) {
    	this.clinicService.saveCompany(owner);
    }
    
    /**
     * Read single Company
     */
    @RequestMapping(value = "/companies/{comId}", method = RequestMethod.GET)
    public CompanyDetail findOwner(@PathVariable("comId") int ownerId) {
        return new CompanyDetail(retrieveOwner(ownerId), clinicService) ;
    }
    
    /**
     * Read single Owner
     */
//    @RequestMapping(value = "/companies/{comId}", method = RequestMethod.GET)
//    public Company findOwner(@PathVariable("comId") int ownerId) {
//        return retrieveOwner(ownerId);
//    }
    /**
     * Read List of Owners
     * @return 
     */
    @GetMapping("/companies/list")
    public Collection<CompanyInfo> findAll() {
        List<CompanyInfo> ret = new LinkedList<>();
        for(Company c: clinicService.findAllCompany())
        {
            ret.add(new CompanyInfo(c));
        }
        return ret; 
    }

//    public Collection<Company> findAll() {
//        return clinicService.findAllCompany(); 
//    }    
    /**
     * Update Owner
     */
    @RequestMapping(value = "/companies/{comId}", method = RequestMethod.PUT)
    public Company updateOwner(@PathVariable("comId") int ownerId, @Valid @RequestBody Company ownerRequest) {
    	Company ownerModel = retrieveOwner(ownerId);
    	// This is done by hand for simplicity purpose. In a real life use-case we should consider using MapStruct.
    	ownerModel.setName(ownerRequest.getName());
    	ownerModel.setAddress(ownerRequest.getAddress());
    	ownerModel.setTaxCode(ownerRequest.getTaxCode());
    	ownerModel.setAddress(ownerRequest.getAddress());
    	ownerModel.setTelephone(ownerRequest.getTelephone());
        this.clinicService.saveCompany(ownerModel);
        return ownerModel;
    }
    
    
    
    static class CompanyInfo
    {
        public int id;
        public String name;
        public String address;


        public String taxCode;
    

        public String info;
    

        public String telephone;
        
        public int commentCount;

        public CompanyInfo( Company c) {
            id = c.getId();
            name = c.getName();
            address = c.getAddress();
            taxCode=c.getTaxCode();
            info = c.getInfo();
            telephone = c.getTelephone();
            commentCount = 0;//c.getComments().size();
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getTaxCode() {
            return taxCode;
        }

        public String getInfo() {
            return info;
        }

        public String getTelephone() {
            return telephone;
        }

        public int getCommentCount() {
            return commentCount;
        }   
    }
    
    static class CompanyDetail extends CompanyInfo
    {
        public List<Comment> comment;
        public CompanyDetail(Company c, ClinicService _clinicService) {
            super(c);
//            System.err.println("CompanyDetail");
            comment = (List<Comment>) _clinicService.findAllCommentByCompanies(c);
//            for(Comment ct:comment)
//            {
//                System.out.println(ct.getId()+"-"+ct.getDetail());
//            }
        }        
    }
}
