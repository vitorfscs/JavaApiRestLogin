package com.accounting.Accounting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.Accounting.Entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;

// Antes de passar pelo banco de dados ele passará por aqui!

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> { // ele extende a classe JpaRepository e chama o UsuarioEntity, Long. 
 Optional<UsuarioEntity> findByUsuario(String usuario); /* Optional vai especificar onde ele quer procurar, ele disse no UsuariEntity, ele dará um Find"Procurar"ByUsuario
    para tentar achar o usuario dentro do banco.
 */
}
