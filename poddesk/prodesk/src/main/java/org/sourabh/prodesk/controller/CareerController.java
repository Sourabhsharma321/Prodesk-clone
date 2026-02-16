package org.sourabh.prodesk.controller;

import org.sourabh.prodesk.entity.Career;
import org.sourabh.prodesk.repository.CareerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
public class CareerController {

    private final CareerRepository repo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public CareerController(CareerRepository repo) {
        this.repo = repo;
        System.out.println("🔥 CareerController LOADED");
    }

    @PostMapping("/careers")
    public ResponseEntity<String> saveCareer(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) MultipartFile resume
    ) {
        try {
            Career career = new Career();
            career.setName(name);
            career.setPhone(phone);
            career.setEmail(email);
            career.setMessage(message);

            // Resume upload
            if (resume != null && !resume.isEmpty()) {
                Path uploadPath = Paths.get(uploadDir).toAbsolutePath();
                Files.createDirectories(uploadPath);

                String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);

                Files.copy(resume.getInputStream(), filePath);
                career.setResumePath(filePath.toString());
            }

            repo.save(career);
            return ResponseEntity.ok("Application submitted successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Application submission failed");
        }
    }
}
