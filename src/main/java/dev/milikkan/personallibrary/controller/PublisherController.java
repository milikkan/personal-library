package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.entity.Publisher;
import dev.milikkan.personallibrary.exception.AuthorNotFoundException;
import dev.milikkan.personallibrary.exception.PublisherNotFoundException;
import dev.milikkan.personallibrary.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/publishers")
@AllArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping({"/", ""})
    public String listAllPublishers(Model model) {
        model.addAttribute("allPublishers", publisherService.findAll());
        return "publisher/list-publishers";
    }

    @GetMapping("/{publisherId}/update")
    public String updatePublisherForm(@PathVariable Long publisherId, Model model) {
        Publisher publisherForUpdate = publisherService.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));

        model.addAttribute("publisherForUpdate", publisherForUpdate);
        return "publisher/update-publisher";
    }

    @PostMapping("/{publisherId}/update")
    public String updatePublisher(
            @PathVariable Long publisherId,
            @ModelAttribute(name = "publisherForUpdate") Publisher publisherForUpdate)
    {
        Publisher oldPublisher = publisherService.findById(publisherId).get();
        oldPublisher.setName(publisherForUpdate.getName());
        oldPublisher.setExplanation(publisherForUpdate.getExplanation());
        publisherService.save(oldPublisher);
        return "redirect:/publishers/";
    }

    @ResponseBody
    @GetMapping("/{publisherId}/books")
    public List<String> getPublisherBookList(@PathVariable Long publisherId) {
        // TODO: add not found exception
        return publisherService.findById(publisherId)
                .get().getBooks().stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @GetMapping("/{publisherId}/delete")
    public String deletePublisher(@PathVariable Long publisherId) {
        publisherService.findById(publisherId)
                .ifPresentOrElse(
                        publisherService::delete,
                        () -> {
                            throw new PublisherNotFoundException(publisherId);
                        }
                );
        return "redirect:/publishers/";
    }
}
