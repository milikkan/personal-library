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

    public Optional<Publisher> sanitizePublisher(Publisher incoming) {
        return this.findAll().stream()
                .filter(p -> p.getName().equals(incoming.getName()))
                .filter(p -> p.getExplanation().equals(incoming.getExplanation()))
                .findAny();
    }
}
