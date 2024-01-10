package com.uas.humanresource.service;

import java.util.List;

import com.uas.humanresource.entity.Staff;


public interface StaffService {
	List<Staff> getAllStaffs();
	
	Staff saveStaff(Staff staff);
	
	Staff getStaffById(Long id);
	
	Staff updateStaff(Staff staff);
	
	void deleteStaffById(Long id);
}
