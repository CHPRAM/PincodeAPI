package com.pincode.dao;

import com.pincode.model.Pincode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeDao extends CrudRepository<Pincode, Integer> {

    Pincode findByPincode(String pincode);
}
