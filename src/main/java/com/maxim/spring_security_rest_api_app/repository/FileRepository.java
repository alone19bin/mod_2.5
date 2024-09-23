package com.maxim.spring_security_rest_api_app.repository;

import com.maxim.spring_security_rest_api_app.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Long> {
}
