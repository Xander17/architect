package ru.geekbrains.atmclient.service;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    List<String> getLogs() throws IOException;

    List<String> getLog(String filename) throws IOException;

    int uploadLogs() throws IOException;

    boolean uploadLog(String filename);

    List<String> getStats();

    List<String> getVideoRecords() throws IOException;

    int uploadVideoRecords() throws IOException;

    boolean uploadVideoRecord(String filename);
}
