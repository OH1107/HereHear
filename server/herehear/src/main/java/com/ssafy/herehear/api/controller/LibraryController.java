package com.ssafy.herehear.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.herehear.api.request.LibraryPostReq;
import com.ssafy.herehear.api.request.LibraryPutReq;
import com.ssafy.herehear.api.response.BaseResponseBody;
import com.ssafy.herehear.api.response.LibraryGetRes;
import com.ssafy.herehear.api.service.LibraryService;
import com.ssafy.herehear.db.entity.Library;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "�� ���� API", tags = {"Library"})
@RestController 
@RequestMapping("/api/v1/libraries")
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	@GetMapping("/{userId}")
	@ApiOperation(value = "���� å ��ȸ")
	public ResponseEntity<List<LibraryGetRes>> getLibrary(
			@RequestBody @ApiParam(value = "���� ����", required = true) @PathVariable Long userId) {
		
		List<LibraryGetRes> libraryList = libraryService.getLibrary(userId);
		
		return ResponseEntity.status(200).body(libraryList);
	}
	
	@PostMapping()
	@ApiOperation(value = "���� å ���")
	public ResponseEntity<?> createLibrary(
			@RequestBody @ApiParam(value = "������ å ����", required = true) LibraryPostReq libraryPostReq) {
		
		Library library = libraryService.createLibrary(libraryPostReq);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
	
	@PutMapping()
	@ApiOperation(value = "���� �� ���� ���� ����")
	public ResponseEntity<?> updateLibrary(
			@RequestBody @ApiParam(value = "����, ��������", required = true) LibraryPutReq libraryPutReq) {
		
		Library library = libraryService.updateLibrary(libraryPutReq);
		
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

}
