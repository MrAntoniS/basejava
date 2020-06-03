package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MainFile {
    public static void main(String[] args) throws MalformedURLException, RemoteException {
//        String filePath = ".\\.gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File("./src/ru/javawebinar/basejava");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String filePath = "C:\\Users\\Антон\\Desktop\\basejava\\basejava";

        int a = 0;
        do {
            File dir = new File(filePath);
            String[] dirList = dir.list();
            if (dirList != null) {
                for (String s : dirList) {
                    File newFile = new File(dir + File.separator + s);
                    if (newFile.isFile()) {
                        System.out.println(dir + File.separator + s);
                    }
                    if (newFile.isDirectory()) {
                        System.out.println(dir + File.separator + s);
                        filePath = newFile + File.separator + s;
                        a = 1;
                    }

                }
            }
        } while (a == 1);
    }
}