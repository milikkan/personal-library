package dev.milikkan.personallibrary.service;

import dev.milikkan.personallibrary.entity.Publisher;
import dev.milikkan.personallibrary.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    public void delete(Publisher publisher) {
        publisherRepository.delete(publisher);
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Optional<Publisher> sanitizePublisher(Publisher incoming) {
        return this.findAll().stream()
                .filter(p -> p.getName().equals(incoming.getName()))
                .filter(p -> p.getExplanation().equals(incoming.getExplanation()))
                .findAny();
    }
}
