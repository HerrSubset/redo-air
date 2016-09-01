package com.realdolmen.redoair.service;

import com.realdolmen.redoair.repository.CategoryRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

@Stateless
@LocalBean
public class CategoryService {
    @Inject
    CategoryRepository repo;

    public List<String> getAllCategoryNames() {
        return repo.findAllNames();
    }
}
