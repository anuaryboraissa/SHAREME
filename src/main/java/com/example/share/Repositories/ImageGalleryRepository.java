package com.example.share.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.ImageGallery;
@Repository
public interface ImageGalleryRepository extends JpaRepository<ImageGallery, Long> {
	@Query(value = "select * from image_gallery  i where i.img_id=?1",nativeQuery = true)
Collection<ImageGallery> getByIdd(long id);
	@Query(value = "select * from image_gallery i inner join std_photos s on s.img_id=i.img_id where s.st_id=?1",nativeQuery = true)
	ImageGallery getStudentPhoto(long id);
}
