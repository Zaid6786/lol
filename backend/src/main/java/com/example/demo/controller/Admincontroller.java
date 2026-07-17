package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Admin;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Save Admin
    @PostMapping("/save")
    public Admin saveAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    // Get All Admins
    @GetMapping("/getall")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    // Get Admin By Id
    @GetMapping("/get/{id}")
    public Optional<Admin> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    // Update Admin
    @PutMapping("/update/{id}")
    public Admin updateAdmin(@PathVariable Long id,
                             @RequestBody Admin admin) {

        return adminService.updateAdmin(id, admin);
    }

    // Delete Admin
    @DeleteMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {

        adminService.deleteAdmin(id);
        return "Admin Deleted Successfully";
    }

}