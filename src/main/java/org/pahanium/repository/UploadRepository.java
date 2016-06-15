package org.pahanium.repository;

import org.pahanium.entity.Upload;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Long> {
    @Query("SELECT u FROM Upload u ORDER BY u.id DESC")
    List<Upload> getLast(Pageable pageable);

    default List<Upload> getLast() {
        return getLast(new PageRequest(0, 5));
    }
}
