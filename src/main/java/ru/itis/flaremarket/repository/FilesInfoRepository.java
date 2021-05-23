package ru.itis.flaremarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.flaremarket.models.FileInfo;

import java.util.Optional;

public interface FilesInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByStorageFileName(String fileName);
}
