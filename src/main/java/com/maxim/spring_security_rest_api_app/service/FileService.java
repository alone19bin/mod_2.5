package com.maxim.spring_security_rest_api_app.service;

import com.maxim.spring_security_rest_api_app.model.File;

import java.io.InputStream;
import java.util.Optional;

public interface FileService {
    void upload(File file);
    InputStream download (File file);
    Optional<String> listFiles();
    void deleteFile(String fileName);
}
