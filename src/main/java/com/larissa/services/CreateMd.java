package com.larissa.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateMd {
    private String path = System.getenv("PATH_RESUMEE_CREATE");

    public void createMd(String name,String resume) throws ResumeeException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String date = now.format(formatter);
        name = date+"_" +"Resumo_"+ name;
        try {
            Files.writeString(Paths.get(path+name), resume, StandardCharsets.UTF_8);
            System.out.println("Arquivo criado com sucesso");
        } catch (IOException e) {
            throw new ResumeeException("Erro ao criar o arquivo: " + e.getMessage());
        }
    }
}
