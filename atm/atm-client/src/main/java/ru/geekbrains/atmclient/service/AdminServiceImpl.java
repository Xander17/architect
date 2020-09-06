package ru.geekbrains.atmclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.atmclient.localstorage.RepositoryStub;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final RepositoryStub repository;

    private final InternalStorage internalStorage;
    private final ExternalStorage externalStorage;

    public List<String> getLogs() throws IOException {
        return internalStorage.getLogFilesList();
    }

    public List<String> getLog(String filename) throws IOException {
        return internalStorage.getLogFileContent(filename);
    }

    public int uploadLogs() throws IOException {
        List<Path> allLogFilePaths = internalStorage.getAllLogFilePaths();
        return externalStorage.uploadFiles(allLogFilePaths);
    }

    public boolean uploadLog(String filename) {
        Optional<Path> logFilePath = internalStorage.getLogFilePath(filename);
        if (logFilePath.isPresent()) {
            return externalStorage.uploadFile(logFilePath.get());
        }
        return false;
    }

    public List<String> getVideoRecords() throws IOException {
        return internalStorage.getVideoFilesList();
    }

    public int uploadVideoRecords() throws IOException {
        List<Path> allVideoFilePaths = internalStorage.getAllVideoFilePaths();
        return externalStorage.uploadFiles(allVideoFilePaths);
    }

    public boolean uploadVideoRecord(String filename) {
        Optional<Path> videoFilePath = internalStorage.getVideoFilePath(filename);
        if (videoFilePath.isPresent()) {
            return externalStorage.uploadFile(videoFilePath.get());
        }
        return false;
    }

    public List<String> getStats() {
        return repository.getAllStats();
    }
}
