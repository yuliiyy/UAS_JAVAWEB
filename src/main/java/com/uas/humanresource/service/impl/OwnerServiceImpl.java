package com.uas.humanresource.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uas.humanresource.entity.Owner;
import com.uas.humanresource.repository.OwnerRepository;
import com.uas.humanresource.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        super();
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).get();
    }

    @Override
    public Owner updateOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteOwnerById(Long id) {
        ownerRepository.deleteById(id);
    }

}
