package dev.milikkan.personallibrary.controller;

import dev.milikkan.personallibrary.entity.Author;
import dev.milikkan.personallibrary.entity.Book;
import dev.milikkan.personallibrary.exception.AuthorNotFoundException;
import dev.milikkan.personallibrary.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping({"/", ""})
    public String listAllAuthors(Model model) {
        model.addAttribute("allAuthors", authorService.findAll());
        return "author/list-authors";
    }

    @ResponseBody
    @GetMapping("/{authorId}/books")
    public List<String> getAuthorBookList(@PathVariable Long authorId) {
        return authorService.findById(authorId)
                .get().getBooks().stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @GetMapping("/{authorId}/update")
    public String updateAuthorForm(@PathVariable Long authorId, Model model) {
        Author authorForUpdate = authorService.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        model.addAttribute("authorForUpdate", authorForUpdate);
        return "author/update-author";
    }

    @PostMapping("/{authorId}/update")
    public String updateAuthor(
            @PathVariable Long authorId,
            @ModelAttribute(name = "authorForUpdate") Author authorForUpdate)
    {
        Author oldAuthor = authorService.findById(authorId).get();
        oldAuthor.setFullName(authorForUpdate.getFullName());
        oldAuthor.setExplanation(authorForUpdate.getExplanation());
        authorService.save(oldAuthor);
        return "redirect:/authors/";
    }

    @GetMapping("/{authorId}/delete")
    public String deleteAuthor(@PathVariable Long authorId) {
        authorService.findById(authorId)
                .ifPresentOrElse(
                        authorService::delete,
                        () -> {
                            throw new AuthorNotFoundException(authorId);
                        });
        return "redirect:/authors/";
    }
}
