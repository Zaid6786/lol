package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.core_user;

public interface CoreUserService {

    core_user saveCoreUser(core_user coreUser);

    List<core_user> getAllCoreUsers();

    Optional<core_user> getCoreUserById(Long id);

    core_user updateCoreUser(Long id, core_user coreUser);

    void deleteCoreUser(Long id);

}