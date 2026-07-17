package com.example.demo.serviceimple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.core_user;
import com.example.demo.repository.CoreUserRepository;
import com.example.demo.service.CoreUserService;

@Service
public class CoreUserServiceImple implements CoreUserService {

    @Autowired
    private CoreUserRepository coreUserRepository;

    @Override
    public core_user saveCoreUser(core_user coreUser) {
        return coreUserRepository.save(coreUser);
    }

    @Override
    public List<core_user> getAllCoreUsers() {
        return coreUserRepository.findAll();
    }

    @Override
    public Optional<core_user> getCoreUserById(Long id) {
        return coreUserRepository.findById(id);
    }

    @Override
    public core_user updateCoreUser(Long id, core_user coreUser) {

        core_user existingCoreUser =
                coreUserRepository.findById(id).orElse(null);

        if (existingCoreUser != null) {

            existingCoreUser.setUsername(coreUser.getUsername());
            existingCoreUser.setEmail(coreUser.getEmail());
            existingCoreUser.setPassword(coreUser.getPassword());
            existingCoreUser.setRole(coreUser.getRole());
            existingCoreUser.setPhoneNumber(coreUser.getPhoneNumber());
            existingCoreUser.setIsActive(coreUser.getIsActive());
            existingCoreUser.setCreatedAt(coreUser.getCreatedAt());

            return coreUserRepository.save(existingCoreUser);
        }

        return null;
    }

    @Override
    public void deleteCoreUser(Long id) {
        coreUserRepository.deleteById(id);
    }

}