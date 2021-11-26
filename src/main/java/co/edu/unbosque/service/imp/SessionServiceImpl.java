package co.edu.unbosque.service.imp;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{

	@Override
	public String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
