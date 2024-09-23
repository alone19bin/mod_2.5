package com.maxim.spring_security_rest_api_app.rest;

import com.maxim.spring_security_rest_api_app.dto.FileDto;
import com.maxim.spring_security_rest_api_app.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/files/")
public class FileRestControllerV1 {

    private  final FileService fileService;

    @Autowired
    public FileRestControllerV1(FileService fileService) {
        this.fileService = fileService;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<FileDto> getFileById(@PathVariable Long id) {
//        File file = fileService.findById(id);
//        if (file == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        FileDto resultFile = FileDto.fromFile(file);
//        return new ResponseEntity<>(resultFile, HttpStatus.OK);
//    }
//
//    @GetMapping("/{name}")
//    public ResponseEntity<FileDto> getFileByName(@PathVariable String name) {
//        File file = fileService.findByFileName(name);
//        if (file == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        FileDto resultFile = FileDto.fromFile(file);
//        return new ResponseEntity<>(resultFile, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<?> listFiles() {
        return ResponseEntity.ok(fileService.listFiles()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No file present in bucket")));
//    public ResponseEntity<List<FileDto>> getAll() {
//        List<File> files = fileService.listFiles();
//        if (files.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        List<FileDto> fileDtoList = FileDto.toFileDtos(files);
//        return new ResponseEntity(fileDtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String fileName) {
        if (fileName == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        fileService.deleteFile(fileName);
        return new ResponseEntity(fileName,HttpStatus.OK);
    }
//    public ResponseEntity<?> deleteFile(@PathVariable("fileName") String fileName) {
//        fileService.deleteFile(fileName);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    @PostMapping
    public ResponseEntity<FileDto> upload(@RequestBody @NonNull FileDto fileDto) {
        fileService.upload(fileDto.toFile());
        return new ResponseEntity("File Uploaded Successfully",HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> download(@RequestBody @NonNull FileDto fileDto) {
        if (StringUtils.hasText(fileDto.getFilename())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name is missing");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                fileDto.getFilename());
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        InputStreamResource resource = new InputStreamResource(fileService.download(fileDto.toFile()));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//                ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(resource);
    }
}
