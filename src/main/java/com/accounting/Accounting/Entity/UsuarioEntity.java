package com.accounting.Accounting.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Anotação para mostrar que aqui é uma entidade
@Table(name = "Usuario") // Anotação para o nome da tabela.
@AllArgsConstructor // Anotação para o lombook construir os Construtores sozinho
@NoArgsConstructor // Anotação para o lombook funcionar junto com JPA
@Data // Anotação para ajudar o AllArgs construir os construtores
@Builder // Anotação para permitir criar instancia de forma fluente. 
public class UsuarioEntity {
    
    @Id // Mostrar que essa private é uma ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração de valor da id, que a estratégia é identity
    private Long Id;

    private String usuario;
    
    private String email;

    private String senha;

}
