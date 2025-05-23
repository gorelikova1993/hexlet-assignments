package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookMapper mapper;

    public List<BookDTO> getAll() {
        var books = repository.findAll();
        var result = books.stream()
                .map(mapper::map)
                .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO data) {
        var book = mapper.map(data);
        if(!authorRepository.existsById(data.getAuthorId())) {
            throw new IllegalArgumentException("Author not found");
        }
        repository.save(book);
        var bookDto = mapper.map(book);
        return bookDto;
    }

    public BookDTO findById(Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var bookDto = mapper.map(book);
        return bookDto;
    }

    public BookDTO update(BookUpdateDTO data, Long id) {
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        mapper.update(data, book);
        repository.save(book);
        var bookDto = mapper.map(book);
        return bookDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
