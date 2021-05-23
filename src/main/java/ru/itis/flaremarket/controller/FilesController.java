package ru.itis.flaremarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.service.FileStorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

@Controller
public class FilesController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/files")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
        String filePath = fileStorageService.saveFile(file);
        return ResponseEntity.ok()
                .body(filePath);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) throws FileNotFoundException {
        fileStorageService.writeFileToResponse(fileName, response);
    }
}
