package com.gomes8.To_Do_List.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BancoDeDados implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try {
            if (dataSource.getConnection() != null) {
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
