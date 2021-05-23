package ru.itis.flaremarket.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

public interface FileStorageService {
    String saveFile(MultipartFile file);
    void writeFileToResponse(String fileName, HttpServletResponse response) throws FileNotFoundException;
}
