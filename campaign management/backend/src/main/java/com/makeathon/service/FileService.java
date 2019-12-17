package com.makeathon.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.makeathon.dto.DTOFactory;
import com.makeathon.dto.ImageDTO;
import com.makeathon.entity.Images;
import com.makeathon.repository.FileRepository;

@Service
public class FileService {
	
//	private static Path SERVER_LOCATION = Paths.get(System.getProperty("user.dir"),"images");
	private static Path SERVER_LOCATION = Paths.get("/mnt");
	private static String PATH_PREFIX = "http://3.18.110.172:30082/";
	
	@Autowired
	private FileRepository fileRepo;
	
    public ImageDTO uploadFile(MultipartFile inFile, String user_id) throws IOException{
    	
    	String fileLoc = Paths.get(SERVER_LOCATION.toString(),inFile.getOriginalFilename()).toString();
		File outPutFile=new File(fileLoc);
		outPutFile.createNewFile();
		FileOutputStream fout=new FileOutputStream(outPutFile);
		fout.write(inFile.getBytes());
		fout.close();
		
		Images imgFile = new Images();
		imgFile.setUrl(PATH_PREFIX+inFile.getOriginalFilename());
		imgFile.setName(inFile.getOriginalFilename());
		imgFile.setUploadedBy(user_id);
		Images savedImage = fileRepo.save(imgFile);
		
		return DTOFactory.getImageDTO(savedImage);
		
    }

	public List<ImageDTO> getAllImages() {

		List<ImageDTO> imagesDTO = new ArrayList<ImageDTO>();
		List<Images> images = fileRepo.findAll();
		images.stream().forEach(img -> imagesDTO.add(DTOFactory.getImageDTO(img)));
		
		return imagesDTO;
	}

	public List<ImageDTO> getAllImagesByUser(String userId) {

		List<ImageDTO> imagesDTO = new ArrayList<ImageDTO>();
		List<Images> images = fileRepo.findByUploadedBy(userId);
		images.stream().forEach(img -> imagesDTO.add(DTOFactory.getImageDTO(img)));
		
		return null;
	}
	
}