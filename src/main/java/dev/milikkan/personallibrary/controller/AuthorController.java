package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping("/")
    public String listAllAuthors(Model model) {

        return "author/list-authors";
    }

    @GetMapping("/{authorId}/update")
    public String updateAuthorForm(@PathVariable Long authorId, Model model) {

        return "author/update-author";
    }

    @PostMapping("/{authorId}/update")
    public String updateAuthor(@ModelAttribute(name = "authorForUpdate") Author authorForUpdate,
                             @PathVariable Long authorId)
    {

        return "redirect:/authors/";
    }

    @GetMapping("/{authorId}")
    public String showAuthorDetails(@PathVariable Long authorId, Model model) {

        return "author/author-details";
    }

    @GetMapping("/{authorId}/delete")
    public String deleteAuthor(@PathVariable Long authorId) {

        return "redirect:/authors/";
    }

}
