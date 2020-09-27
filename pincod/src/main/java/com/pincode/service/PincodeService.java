package com.pincode.service;

import java.util.List;

import com.pincode.model.Pincode;
import com.pincode.model.PincodeDto;

public interface PincodeService {

    Pincode save(PincodeDto pincode);
    List<Pincode> findAll();
    void delete(int id);

    Pincode findOne(String pincode);

    Pincode findById(int id);

    PincodeDto update(PincodeDto pincodeDto);
}
