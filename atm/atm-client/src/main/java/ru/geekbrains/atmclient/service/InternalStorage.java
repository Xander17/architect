package ru.geekbrains.atmclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.atmclient.exception.ApplicationPropertiesException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InternalStorage {

    @Value("${app.internal-storage.path}")
    private String internalPath;
    @Value("${app.internal-storage.logs}")
    private String logsDir;
    @Value("${app.internal-storage.video-records}")
    private String videoDir;

    private Path path;
    private Path logsPath;
    private Path videoPath;

    @PostConstruct
    public void init() {
        path = Paths.get(internalPath);
        logsPath = path.resolve(logsDir);
        videoPath = path.resolve(videoDir);
        if (Files.notExists(logsPath)) {
            throw new ApplicationPropertiesException("Logs path not exists");
        }
        if (Files.notExists(videoPath)) {
            throw new ApplicationPropertiesException("Video path not exists");
        }
    }

    public List<String> getLogFilesList() throws IOException {
        return getFilesList(logsPath);
    }

    public List<String> getLogFileContent(String file) throws IOException {
        Optional<Path> filePath = getLogFilePath(file);
        if (filePath.isPresent()) {
            return Files.readAllLines(filePath.get());
        }
        return Collections.emptyList();
    }

    public Optional<Path> getLogFilePath(String file) {
        return getPathOrEmpty(file, logsPath);
    }

    public List<Path> getAllLogFilePaths() throws IOException {
        return getAllFilePaths(logsPath);
    }

    public List<String> getVideoFilesList() throws IOException {
        return getFilesList(videoPath);
    }

    public Optional<Path> getVideoFilePath(String file) {
        return getPathOrEmpty(file, videoPath);
    }

    public List<Path> getAllVideoFilePaths() throws IOException {
        return getAllFilePaths(videoPath);
    }

    private List<String> getFilesList(Path videoPath) throws IOException {
        return Files.list(videoPath)
                .filter(file -> Files.isRegularFile(file))
                .map(file -> file.getFileName().toString())
                .collect(Collectors.toList());
    }

    private Optional<Path> getPathOrEmpty(String file, Path dirPath) {
        Path filePath = dirPath.resolve(file);
        if (Files.notExists(filePath)) {
            return Optional.empty();
        }
        return Optional.of(filePath);
    }

    private List<Path> getAllFilePaths(Path videoPath) throws IOException {
        return Files.list(videoPath)
                .filter(file -> Files.isRegularFile(file))
                .collect(Collectors.toList());
    }
}
