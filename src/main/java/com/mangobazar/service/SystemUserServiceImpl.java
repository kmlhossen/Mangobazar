package com.mangobazar.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mangobazar.dto.SystemUserDto;
import com.mangobazar.exception.DuplicateEntryException;
import com.mangobazar.model.SystemUser;
import com.mangobazar.model.UserRole;
import com.mangobazar.repository.SystemUserRepository;
import com.mangobazar.util.RoleType;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

	static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private final SystemUserRepository systemUserRepository;

	@Autowired
	public SystemUserServiceImpl(SystemUserRepository repository) {
		systemUserRepository = repository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
	public SystemUser getUserByEmail(String email) {
		return systemUserRepository.findOneByEmail(email);
	}

	@Override
	public boolean checkPassword(String userPassword, String givenPassword) {
		if (userPassword != null && givenPassword != null) {
			return passwordEncoder.matches(givenPassword, userPassword);
		}
		return false;
	}

	@Override
	public SystemUser createUser(SystemUserDto systemUserDto) throws DuplicateEntryException {

		if (systemUserRepository.findOneByEmail(systemUserDto.getEmail()) != null) {
			throw new DuplicateEntryException("User name already exist");
		}

		SystemUser systemUser = new SystemUser();
		systemUser.setEmail(systemUserDto.getEmail());
		systemUser.setEmail(systemUserDto.getEmail());

		// encrypt the password
		systemUser.setPassword(passwordEncoder.encode(systemUserDto.getPassword().trim()));

		systemUser.setFirstName(systemUserDto.getFirstName());
		systemUser.setLastName(systemUserDto.getLastName());
		systemUser.setAddress(systemUserDto.getAddress());
		systemUser.setContactNo(systemUserDto.getContactNo());

		
		// assign a default role customer
		Set<UserRole> userRoleList = new HashSet<UserRole>();
		UserRole userRole=new UserRole();
		userRole.setId(RoleType.ROLE_CUSTOMER.getValue());
		userRoleList.add(userRole);
		systemUser.setRoles(userRoleList);

		return systemUserRepository.saveAndFlush(systemUser);
	}

	@Override
	public void updateLogOutTime(String userName) {
		SystemUser systemUser = getUserByEmail(userName);
		if (systemUser != null) {
			systemUser.setLastLogOut(new Date(System.currentTimeMillis()));
		}
	}

	@Override
	public boolean hasAccess(SystemUser user, String userName) {
		SystemUser systemUser = getUserByEmail(userName);
		return systemUser != null;

	}
}
