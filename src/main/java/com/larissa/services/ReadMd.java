package com.larissa.services;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadMd {
    private final String path = System.getenv("PATH_RESUMEE_READ")+ "\\";
    public String read(String file) throws ResumeeException {
        try {
            String content = Files.readString(Paths.get(path+file), StandardCharsets.UTF_8);
            String fileName = file.substring(0, file.length() - 3);
            return fileName+"\n" +content;
        } catch (IOException e) {
            throw new ResumeeException("Erro ao ler o arquivo: " + e.getMessage());
        }catch (StringIndexOutOfBoundsException e) {
            throw new ResumeeException("Erro: O arquivo '" + file + "' não possui a extensão .md ou o nome é muito curto.");
        }
    }
}
