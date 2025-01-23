package com.larissa.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class VerifyFiles {
    private final String path = System.getenv("PATH_RESUMEE_READ");

    private Boolean exists(File directory){
        return directory.exists() && directory.isDirectory();
    }

    private Boolean hasFiles(File[] files){
        return files != null && files.length > 0;
    }

    public ArrayList<String> verifyFiles() throws ResumeeException {
        ArrayList<String> filesName = new ArrayList<>();
        File directory = new File(path);
        if (!exists(directory)){
            throw new ResumeeException("Caminho inválido ou não é um diretório.");
        }
        File[] files = directory.listFiles();
        if (!hasFiles(files)){
            throw new ResumeeException("Nenhum arquivo encontrado");
        }

        LocalDate today = LocalDate.now();

        for (File file : files){
            Path pathFile = Paths.get(file.getAbsolutePath());
            try {
                BasicFileAttributes attributes = Files.readAttributes(pathFile, BasicFileAttributes.class);
                LocalDateTime modificationDate = LocalDateTime.ofInstant(attributes.lastModifiedTime().toInstant(), ZoneId.systemDefault());
                if (modificationDate.toLocalDate().equals(today)){
                    filesName.add(file.getName());
                }
            } catch (IOException e) {
                throw new ResumeeException("Erro ao tentar ver atributos do arquivo: " + file.getName());
            }
        }
        return filesName;
    }

}
