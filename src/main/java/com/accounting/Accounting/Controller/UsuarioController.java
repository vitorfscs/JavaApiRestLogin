package com.accounting.Accounting.Controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounting.Accounting.DTO.UsuarioDto;
import com.accounting.Accounting.Service.UsuarioService;

@RestController // Anotação para indicar que aqui é um controle.
@RequestMapping("/usuario") // Requisição para a raiz seja localhost:8080/usuario
public class UsuarioController {
    
    @Autowired // Permitir injeção automaticas de dependencia nas classe, métodos e atributos. No Controller é muito usado para chamar o Service
    private  UsuarioService usuarioService;

    @PostMapping("/cadastrar") // Endpoint Criação de usuário
    public ResponseEntity<String> cadastrar(@RequestBody UsuarioDto dto){

        try{ 
        // Chamar Service para cadastrar
            usuarioService.cadastrarUsuario(dto); // Chamando o service, + a classe e o dto.
        //Retornar 201 Create
            return ResponseEntity.status(200).body("Usuario Registrado com sucesso!: 201"); // chama o Response Entity para responder a requisição e mostrar se tem ou não error no status
        }catch(Exception e){ // Error no servidor.
            return ResponseEntity.status(500).body("Error no servidor: 500"); // chama o Response Entity para responder a requisição e mostrar se tem ou não error no status
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDto dto){
        try {
            
            // Chamar o Service para autenticar
            boolean autenticado = usuarioService.autenticar(dto); // Um booleano chamando na service a classe autenticar para verificar se o hash é verdadeiro.
            // Se true -> 200 OK
            if(autenticado){ // Se autenticado for verdadeiro,
            return ResponseEntity.ok("Logado com sucesso: 201"); // chama o Response Entity para responder a requisição e mostrar se tem ou não error no status
            // Se não -> 401  Unoauthorizede
            }else{ // Se não gerará o error. 
            return ResponseEntity.status(401).body("Usuário ou senha errado: 401"); // chama o Response Entity para responder a requisição e mostrar se tem ou não error no status
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro no servidor: 500"); // chama o Response Entity para responder a requisição e mostrar se tem ou não error no status
        }
    }

}
