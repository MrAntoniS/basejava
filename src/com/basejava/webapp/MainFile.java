package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MainFile {
    public static void main(String[] args) throws MalformedURLException, RemoteException {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\Users\\Антон\\Desktop\\basejava\\basejava\\");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String symbol = "--";
        showDirectory(dir, symbol);
    }

    public static void showDirectory(File dir, String symbol) {
        File[] dirList = dir.listFiles();
        if (dirList != null) {
            for (File file : dirList) {
                if (file.isFile()) {
                    System.out.println(symbol + file.getName());
                }
                if (file.isDirectory()) {
                    System.out.println(symbol + file.getName() + "/");
                    showDirectory(file, symbol + "--");
                }
            }
        }
    }
}