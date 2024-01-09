package com.uas.humanresource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uas.humanresource.entity.Owner;
import com.uas.humanresource.entity.Staff;

public interface OwnerRepository extends JpaRepository<Owner, Long>{

}
