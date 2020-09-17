package ru.geekbrains.atmclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.exception.ApplicationPropertiesException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class ExternalStorage {

    @Value("${app.external-storage.path}")
    private String externalPath;

    private Path path;

    @PostConstruct
    public void init() throws IOException {
        path = Paths.get(externalPath);
        if (Files.exists(path) && !Files.isDirectory(path)) {
            throw new ApplicationPropertiesException("External path is not directory");
        }
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
    }

    public boolean uploadFile(Path file) {
        try {
            Files.copy(file, path.resolve(file.getFileName()), REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int uploadFiles(List<Path> files) {
        int count = 0;
        for (Path file : files) {
            if (uploadFile(file)) {
                count++;
            }
        }
        return count;
    }
}
