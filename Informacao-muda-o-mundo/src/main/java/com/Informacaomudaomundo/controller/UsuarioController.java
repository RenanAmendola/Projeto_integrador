package com.Informacaomudaomundo.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Informacaomudaomundo.model.Usuario;
import com.Informacaomudaomundo.model.UsuarioLogin;
import com.Informacaomudaomundo.repository.UsuarioRepository;
import com.Informacaomudaomundo.service.UsuarioService;



@RestController
@RequestMapping("/Usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
@Autowired
private UsuarioService UsuarioService;
	
@Autowired
private UsuarioRepository UsuRe;


@GetMapping("/all")
public ResponseEntity<List<Usuario>> getAll(){
	return ResponseEntity.ok(UsuRe.findAll());
}


@GetMapping("/{id}")
public ResponseEntity<Usuario> getById(@PathVariable long id){
	return UsuRe.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
}

@PostMapping("/logar")
public ResponseEntity<UsuarioLogin> autenticationUsuario(@RequestBody Optional<UsuarioLogin> usuario){
	
	return UsuarioService.logarUsuario(usuario)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
}
	
@PostMapping("/cadastrar")
public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
	return UsuarioService.cadastrarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
}
	
@PutMapping("/atualizar")
public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
	return UsuarioService.atualizarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

}
