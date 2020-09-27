package com.pincode.service;



import com.pincode.dao.UserDao;
import com.pincode.dao.PincodeDao;
import com.pincode.dao.UserDao;
import com.pincode.model.Pincode;
import com.pincode.model.PincodeDto;
import com.pincode.model.User;
//import com.pincode.model.UserDto;
import com.pincode.service.PincodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service(value = "pincodeService")
public class PincodeServiceImpl implements  PincodeService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PincodeDao pincodeDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	/*
	 * public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { User user = userDao.findByUsername(username);
	 * if(user == null){ throw new
	 * UsernameNotFoundException("Invalid username or password."); } return new
	 * org.springframework.security.core.userdetails.User(user.getName(),
	 * user.getPassword(), getAuthority()); }
	 * 
	 * private List<SimpleGrantedAuthority> getAuthority() { return
	 * Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")); }
	 */

	public List<Pincode> findAll() {
		List<Pincode> list = new ArrayList<>();
		pincodeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		pincodeDao.deleteById(id);
	}

	@Override
	public Pincode findOne(String pincode) {
		return pincodeDao.findByPincode(pincode);
	}

	@Override
	public Pincode findById(int id) {
		Optional<Pincode> optionalUser = pincodeDao.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

    @Override
    public PincodeDto update(PincodeDto pincodeDto) {
        Pincode pincode = findById(pincodeDto.getId());
        if(pincode != null) {
            BeanUtils.copyProperties(pincodeDto, pincode, "password");
            pincodeDao.save(pincode);
        }
        return pincodeDto;
    }

    @Override
    public Pincode save(PincodeDto pincode) {
	    Pincode newPincode = new Pincode();
	    newPincode.setUsername(pincode.getUsername());
	    newPincode.setFirstName(pincode.getFirstName());
	    newPincode.setLastName(pincode.getLastName());
	    newPincode.setPassword(bcryptEncoder.encode(pincode.getPassword()));
	   // newPincode.setAge(pincode.get);
	    newPincode.setPincode(pincode.getPincode());
        return pincodeDao.save(newPincode);
    }
}
