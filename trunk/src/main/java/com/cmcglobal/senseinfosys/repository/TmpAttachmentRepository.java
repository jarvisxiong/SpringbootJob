package com.cmcglobal.senseinfosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cmcglobal.senseinfosys.model.TmpAttachment;

@Repository("tmpAttachmentRepository")
public interface TmpAttachmentRepository extends JpaRepository<TmpAttachment, Integer> {
	 TmpAttachment findById(int id);
}
