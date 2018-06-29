package service;

import entity.Usuario;

public interface LoginService {
	Usuario login(String username, String password) throws IllegalArgumentException;

}
