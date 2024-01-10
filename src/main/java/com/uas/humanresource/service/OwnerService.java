package com.uas.humanresource.service;

import java.util.List;

import com.uas.humanresource.entity.Owner;
import com.uas.humanresource.entity.Staff;


public interface OwnerService {
	List<Owner> getAllOwners();
	
	Owner saveOwner(Owner owner);

	Owner getOwnerById(Long id);

	Owner updateOwner(Owner owner);
	
	void deleteOwnerById(Long id);
}
