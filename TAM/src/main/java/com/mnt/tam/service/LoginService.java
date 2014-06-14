package com.mnt.tam.service;

import java.util.List;

public interface LoginService {
	public List<Object[]> getCredentials(String username,String password);
}
