package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public List<AuthorDTO> getAll() {
        var authors = repository.findAll();
        var result = authors.stream()
                .map(mapper::map)
                .toList();
        return result;
    }

    public AuthorDTO create(AuthorCreateDTO authorData) {
        var author = mapper.map(authorData);
        repository.save(author);
        var authorDto = mapper.map(author);
        return authorDto;
    }

    public AuthorDTO findById(Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var authorDto = mapper.map(author);
        return authorDto;
    }

    public AuthorDTO update(AuthorUpdateDTO authorData, Long id) {
        var author = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        mapper.update(authorData, author);
        repository.save(author);
        var authorDto = mapper.map(author);
        return authorDto;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
