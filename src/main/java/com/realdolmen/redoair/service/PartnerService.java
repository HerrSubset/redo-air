package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Partner;
import com.realdolmen.redoair.repository.PartnerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class PartnerService {
    @Inject
    PartnerRepository repo; // package scope so Mockito can inject it


    public List<Partner> getAllPartners() {
        return repo.findAll();
    }
}


