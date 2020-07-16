package com.MySiteTest.service.Imp;

import com.MySiteTest.Utils.FileOperations;
import com.MySiteTest.service.ReturnFileModelService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.io.FileNotFoundException;

/**
 * return file model to html
 */
@Component
public class ReturnFileModelImp implements ReturnFileModelService {
    @Override
    public void getFiles(String dirName,Model model) throws FileNotFoundException {
        model.addAttribute("filename",FileOperations.gainFilename(dirName));
        model.addAttribute("sizes",FileOperations.gainSize(dirName));
    }
}
