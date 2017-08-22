package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.FileAttachment;

@Repository("fileAttachmentRepository")
public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {
	 FileAttachment findById(long id);
}
