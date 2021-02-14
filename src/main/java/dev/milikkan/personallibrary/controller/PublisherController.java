package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    @GetMapping("/")
    public String listAllPublishers(Model model) {

        return "publisher/list-publishers";
    }

    @GetMapping("/{publisherId}/update")
    public String updatePublisherForm(@PathVariable Long publisherId, Model model) {

        return "publisher/update-publisher";
    }

    @PostMapping("/{publisherId}/update")
    public String updatePublisher(@ModelAttribute(name = "publisherForUpdate") Publisher publisherForUpdate,
                               @PathVariable Long publisherId)
    {

        return "redirect:/publishers/";
    }

    @GetMapping("/{publisherId}")
    public String showPublisherDetails(@PathVariable Long publisherId, Model model) {

        return "publisher/publisher-details";
    }

    @GetMapping("/{publisherId}/delete")
    public String deletePublisher(@PathVariable Long publisherId) {

        return "redirect:/publishers/";
    }
}
