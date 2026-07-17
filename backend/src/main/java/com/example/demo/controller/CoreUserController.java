package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.core_user;
import com.example.demo.service.CoreUserService;

@RestController
@RequestMapping("/coreuser")
@CrossOrigin(origins = "*")
public class CoreUserController {

    @Autowired
    private CoreUserService coreUserService;

    // Save Core User
    @PostMapping("/save")
    public core_user saveCoreUser(@RequestBody core_user coreUser) {
        return coreUserService.saveCoreUser(coreUser);
    }

    // Get All Core Users
    @GetMapping("/getall")
    public List<core_user> getAllCoreUsers() {
        return coreUserService.getAllCoreUsers();
    }

    // Get Core User By Id
    @GetMapping("/get/{id}")
    public Optional<core_user> getCoreUserById(@PathVariable Long id) {
        return coreUserService.getCoreUserById(id);
    }

    // Update Core User
    @PutMapping("/update/{id}")
    public core_user updateCoreUser(@PathVariable Long id,
                                    @RequestBody core_user coreUser) {

        return coreUserService.updateCoreUser(id, coreUser);
    }

    // Delete Core User
    @DeleteMapping("/delete/{id}")
    public String deleteCoreUser(@PathVariable Long id) {

        coreUserService.deleteCoreUser(id);
        return "Core User Deleted Successfully";
    }

}