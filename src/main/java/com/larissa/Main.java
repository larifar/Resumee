package com.larissa;

import com.larissa.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        VerifyFiles verifyFiles = new VerifyFiles();
        ReadMd reader = new ReadMd();
        RequestGemini gemini = new RequestGemini();
        CreateMd creator = new CreateMd();

        ArrayList<String> files;
        try{
            files = verifyFiles.verifyFiles();
            System.out.println(files);
            for (String file : files){
                String resume = reader.read(file);
                String response = gemini.request(resume);
                creator.createMd(file, response);
                System.out.println(response);
            }
        } catch (ResumeeException e){
            System.out.println(e.getMessage());
        }
    }
}
