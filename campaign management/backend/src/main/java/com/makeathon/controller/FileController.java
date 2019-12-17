package com.makeathon.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.makeathon.dto.ImageDTO;
import com.makeathon.service.FileService;

import io.swagger.annotations.Api;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/files")
@Api(value = "Files upload and download APIs", tags = { "files" })
public class FileController {

//	private final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	FileService fileService;
	
	@PostMapping(path="/uploadFile/{user_id}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ImageDTO uploadFileToServer(	@RequestBody @Valid MultipartFile inFile,
    									@PathVariable("user_id") @Valid String user_id) throws IOException{
		
		return fileService.uploadFile(inFile, user_id);
		
	}
	
	@GetMapping("/getAll")
	public List<ImageDTO> fetchAllImages() {
		
		return fileService.getAllImages();
		
	}

	@GetMapping("/getAll/{user_id}")
	public List<ImageDTO> fetchAllImagesForUser(@PathVariable("user_id") String userId) {
		
		return fileService.getAllImagesByUser(userId);
		
	}
}
