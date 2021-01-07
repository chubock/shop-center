package com.yubar.shopcenter.fileservice.repository;


import com.yubar.shopcenter.fileservice.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by Yubar on 1/15/2017.
 */

public interface FileRepository extends JpaRepository<File, String> {
}
