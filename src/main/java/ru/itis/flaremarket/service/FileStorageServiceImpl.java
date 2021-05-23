package ru.itis.flaremarket.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.flaremarket.models.FileInfo;
import ru.itis.flaremarket.repository.FilesInfoRepository;
import ru.itis.flaremarket.security.details.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FilesInfoRepository filesInfoRepository;

    @Autowired
    private UserService userService;

    @Value("${storage.path}")
    private String storagePath;

    @Override
    public String saveFile(MultipartFile uploadingFile) {
        FileInfo file;
        String storageName = UUID.randomUUID().toString() + "." + uploadingFile.getOriginalFilename();
        file = FileInfo.builder()
                .type(uploadingFile.getContentType())
                .originalFileName(uploadingFile.getOriginalFilename())
                .storageFileName(storageName)
                .userId(userService.getUserByEmail(((UserDetailsImpl) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal()).getUsername()).getId())
                .size(uploadingFile.getSize())
                .url(storagePath + "\\" + storageName)
                .build();

        try {
            Files.copy(uploadingFile.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        filesInfoRepository.save(file);
        return file.getStorageFileName();
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) throws FileNotFoundException {
        Optional<FileInfo> fileInfo = filesInfoRepository.findByStorageFileName(fileName);
        if(!fileInfo.isPresent()) throw new FileNotFoundException("Файл не найден");
        response.setContentType(fileInfo.get().getType());
        try {
            FileInputStream fis = new FileInputStream(fileInfo.get().getUrl());
            IOUtils.copy(fis, response.getOutputStream());
            fis.close();
            response.flushBuffer();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
