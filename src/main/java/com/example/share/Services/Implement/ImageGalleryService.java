package com.example.share.Services.Implement;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.share.Entities.ImageGallery;
import com.example.share.Repositories.ImageGalleryRepository;

@Service
public class ImageGalleryService {

	@Autowired
	private ImageGalleryRepository imageGalleryRepository;
	
	public void saveImage(ImageGallery imageGallery) {
		imageGalleryRepository.save(imageGallery);	
	}

	public List<ImageGallery> getAllActiveImages() {
		return imageGalleryRepository.findAll();
	}

	public Collection<ImageGallery> getImageById(Long id) {
		Collection<ImageGallery> imgs=imageGalleryRepository.getByIdd(id);
		return imgs;
	}
	public Optional<ImageGallery> getImageByIdd(long id){
		return imageGalleryRepository.findById(id);
   }
	public ImageGallery getStudentPhotoById(long id){
		return imageGalleryRepository.getStudentPhoto(id);
   }
}
