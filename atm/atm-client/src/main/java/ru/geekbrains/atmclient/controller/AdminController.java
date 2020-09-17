package ru.geekbrains.atmclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.atmclient.handler.AuthHandler;
import ru.geekbrains.atmclient.service.AdminService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AuthHandler authHandler;
    private final AdminService adminService;

    @GetMapping
    public String mainPage() {
        return "adminPage";
    }

    @GetMapping("/auth")
    public String authPage() {
        return "auth";
    }

    @PostMapping("/authorization")
    public String auth(String pinCode) {
        authHandler.authByCard(pinCode);
        return "redirect:/admin";
    }

    @PostMapping("/close")
    public String endSession() {
        authHandler.endTokenSession();
        return "redirect:/admin/auth";
    }

    @GetMapping("/stat")
    public String statPage(Model model) {
        authHandler.checkAuthorized();
        List<String> stats = adminService.getStats();
        model.addAttribute("stats", stats);
        return "stat";
    }

    @GetMapping("/logs")
    public String logsPage(Model model) throws IOException {
        authHandler.checkAuthorized();
        List<String> logs = adminService.getLogs();
        model.addAttribute("logs", logs);
        return "logs";
    }

    @GetMapping("/logs/{filename}")
    public String logsPage(Model model, @PathVariable("filename") String filename) throws IOException {
        authHandler.checkAuthorized();
        List<String> logLines = adminService.getLog(filename);
        model.addAttribute("log", logLines);
        return "logs";
    }

    @GetMapping("/logsDownload")
    public String downloadAllLogs(Model model) throws IOException {
        authHandler.checkAuthorized();
        int count = adminService.uploadLogs();
        model.addAttribute("filesDownloaded", count);
        return "redirect:/admin/logs";
    }

    @GetMapping("/logsDownload/{filename}")
    public String downloadLog(Model model, @PathVariable("filename") String filename) {
        authHandler.checkAuthorized();
        boolean status = adminService.uploadLog(filename);
        model.addAttribute("fileDownloadStatus", status);
        return "redirect:/admin/logs";
    }

    @GetMapping("/video")
    public String videoRecordsPage(Model model) throws IOException {
        authHandler.checkAuthorized();
        List<String> videoRecords = adminService.getVideoRecords();
        model.addAttribute("recordsList", videoRecords);
        return "videoRecords";
    }

    @GetMapping("/videoDownload")
    public String downloadAllVideoRecords(Model model) throws IOException {
        authHandler.checkAuthorized();
        int count = adminService.uploadVideoRecords();
        model.addAttribute("filesDownloaded", count);
        return "redirect:/admin/video";
    }

    @GetMapping("/videoDownload/{filename}")
    public String downloadVideoRecord(Model model, @PathVariable("filename") String filename) {
        authHandler.checkAuthorized();
        boolean status = adminService.uploadVideoRecord(filename);
        model.addAttribute("fileDownloadStatus", status);
        return "redirect:/admin/video";
    }
}
