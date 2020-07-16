package com.MySiteTest.service;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;

@Component
public interface ReturnFileModelService {
    public void getFiles(String dirName,Model model) throws FileNotFoundException;
}
