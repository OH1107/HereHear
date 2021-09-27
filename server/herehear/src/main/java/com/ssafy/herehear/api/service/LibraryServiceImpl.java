package com.ssafy.herehear.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.herehear.api.request.LibraryPostReq;
import com.ssafy.herehear.api.request.LibraryPutReq;
import com.ssafy.herehear.db.entity.Account;
import com.ssafy.herehear.db.entity.Book;
import com.ssafy.herehear.db.entity.Library;
import com.ssafy.herehear.db.repository.AccountRepository;
import com.ssafy.herehear.db.repository.LibraryRepository;

@Service("LibraryService")
public class LibraryServiceImpl implements LibraryService {
	
	@Autowired
	LibraryRepository libraryRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	LibraryService libraryService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	BookService bookService;
	
	
	@Override
	public Library findByLibraryId(Long library_id) {
		Library library = libraryRepository.findById(library_id).get();
		return library;
	}
	
	@Override
	public Library createLibrary(LibraryPostReq libraryPostReq) {
		Library library = new Library();
		Book book = bookService.findByBookId(libraryPostReq.getBook_id());
		library.setBook(book);
		
		Account account = accountService.findByAccountId(libraryPostReq.getUser_id());
		library.setAccount(account);

		library.setStars(0);
		library.setRead_status(0);
		library.setFlag(false);
		
		return libraryRepository.save(library);
	}
	
	@Override
	public Library updateLibrary(LibraryPutReq libraryPutReq) {
		Library library = libraryService.findByLibraryId(libraryPutReq.getId());
		Book book = bookService.findByBookId(library.getBook().getId());
		
		// ������ ó�� �ִ� ���, flag �ٲٰ� å ������ stars_count�� stars_sum �����ֱ�
		if (library.getFlag() == false) {
			library.setFlag(true);
			book.setStars_count(book.getStars_count()+1);
			book.setStars_sum(book.getStars_sum()+libraryPutReq.getStars());
		} 
		// ���� ������ �ִ� ���, å �������� stars_count�� ���� ���� ���ְ� ���ο� ���� ���ϱ�
		else {
			book.setStars_sum(book.getStars_sum()-library.getStars());
			book.setStars_sum(book.getStars_sum()+libraryPutReq.getStars());
		}
		// å ������ stars_count�� stars_sum ���� ��, library�� �� ����
		library.setStars(libraryPutReq.getStars());
		library.setRead_status(libraryPutReq.getRead_status());
		
		return libraryRepository.save(library);
	}

}
