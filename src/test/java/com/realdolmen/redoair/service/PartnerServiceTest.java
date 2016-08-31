package com.realdolmen.redoair.service;

import com.realdolmen.redoair.domain.Partner;
import com.realdolmen.redoair.repository.PartnerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PartnerServiceTest {
    @InjectMocks
    private PartnerService service;

    @Mock
    private PartnerRepository repository;


    @Test
    public void getAllPartnersReturnsTheFullListFromDB() {
        List<Partner> partners = new ArrayList<Partner>();
        Mockito.when(repository.findAll()).thenReturn(partners);

        Assert.assertSame(service.getAllPartners(), partners);
    }
}
