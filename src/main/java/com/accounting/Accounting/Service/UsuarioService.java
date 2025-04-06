package com.accounting.Accounting.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.accounting.Accounting.DTO.UsuarioDto;
import com.accounting.Accounting.Entity.UsuarioEntity;
import com.accounting.Accounting.Repository.UsuarioRepository;

@Service // Anotação para definir essa .java como serviço.
public class UsuarioService {
    
    @Autowired // Permite injeção automatica de dependências em classe, métodos, atributos e construtores. No Service é usado muito para chamar repository 
    private UsuarioRepository usuarioRepository; // Chamando a classe UsuarioRepository.java

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // Chamando o método de Criptografia, então sempre chame com final. 

    public void cadastrarUsuario(UsuarioDto dto){ // Chamando o UsuarioDto para passar os dados sem interferencia.  
        // Criptografar senha
            String senhaCriptografada = encoder.encode(dto.senha()); // Vamos criar uma variavel chamada SenhaTANANAN, chamar o encoder de cima com .encoder que chamara o DTO
       
            //Criar entidade
        UsuarioEntity usuario = UsuarioEntity.builder() // Chamamos a entity e chamamos de usuario oou outro nome, e ela vai ser igual a entity.biulder() para transformar em entidade 
        .usuario(dto.usuario()) // chamando o dto para o usuario
        .senha(senhaCriptografada) // chamando o dto para a senha
        .email(dto.email()) // chamando o dto para o email
        .build() // implementar a entidade
        ;

        ; 
        //Salvar no banco
        usuarioRepository.save(usuario); // Chamando o Repositorio e concatenando com save e chamando a classe. 
    }

    public boolean autenticar(UsuarioDto dto){ // Login com autenticação de usuário, para ver se a senha é verdadeira. etc...
        // Buscar por usuario
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findByUsuario(dto.usuario()); /* Optional vai especificar onde ele quer procurar, ele disse no UsuariEntity, ele dará um Find"Procurar"ByUsuario
        para tentar achar o usuario dentro do banco */
        
        // Verificar Senha com Bcrypt

        if (usuarioOptional.isPresent()){ // Se o UsuarioOptional.isPresent, o isPresent é pra verificar se tem algum valor nulo ou não!
            UsuarioEntity usuario = usuarioOptional.get(); // Para pegar o valor do banco de dados. GET.
            return encoder.matches(dto.senha(), usuario.getSenha()); //  Verifica se a senha informada no login bate com a senha criptografada do banco usando matches.

        }
        // Return True ou False

        return false; 
    }



}
