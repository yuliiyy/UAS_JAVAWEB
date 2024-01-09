package com.uas.humanresource.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uas.humanresource.entity.Staff;
import com.uas.humanresource.repository.StaffRepository;
import com.uas.humanresource.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;
    
    public StaffServiceImpl(StaffRepository staffRepository) {
        super();
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAllStaffs() {
        return staffRepository.findAll();
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).get();
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);   
    }

}
