package com.jpmc.fileUpload;

import com.jpmc.fileUpload.entity.EDW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdwRepository extends JpaRepository<EDW,Integer> {
}
