package com.pincode.controller;

import com.pincode.model.ApiResponse;
import com.pincode.model.Pincode;
import com.pincode.model.PincodeDto;
import com.pincode.model.User;
import com.pincode.service.PincodeService;
//import com.pincode.model.UserDto;
import com.pincode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pincodes")
public class PincodeController {

    @Autowired
    private PincodeService pincodeService;

    @PostMapping
    public ApiResponse<User> savePincode(@RequestBody PincodeDto pincode){
        return new ApiResponse<>(HttpStatus.OK.value(), "Pincode saved successfully.",pincodeService.save(pincode));
    }

    @GetMapping
    public ApiResponse<List<Pincode>> listPincode(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Pincode list fetched successfully.",pincodeService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Pincode> getOne(@PathVariable int id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Pincode Service fetched successfully.",pincodeService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<PincodeDto> update(@RequestBody PincodeDto userDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Pincode Service updated successfully.",pincodeService.update(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        pincodeService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Pincode deleted successfully.", null);
    }



}
