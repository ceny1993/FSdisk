package com.ceny.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chensongkui on 2017/3/27.
 */
@Repository
public interface FileInfoRepo extends JpaRepository<FileInfo,Long> {
    List<FileInfo> findAllByParentIdAndIsDelete(long parentId,boolean isDelete);
}
