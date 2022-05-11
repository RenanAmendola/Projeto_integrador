package com.Informacaomudaomundo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Informacaomudaomundo.model.Usuario;

import com.Informacaomudaomundo.repository.UsuarioRepository;

@Service
public class UserDetailsServicelmpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository UsuRe;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail)
	throws UsernameNotFoundException{
		
		Optional<Usuario> usuario = UsuRe.findByEmail(userEmail);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userEmail + "Não encontrado"));
		
		return usuario.map(UserDetailslmpl::new).get();
	}
}
