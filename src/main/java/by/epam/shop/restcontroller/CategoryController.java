package by.epam.shop.restcontroller;

import by.epam.shop.dto.CategoryDTO;
import by.epam.shop.dto.CategoryDTOToCategory;
import by.epam.shop.dto.CategoryToCategoryDTO;
import by.epam.shop.service.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    private final CategoryDTOToCategory categoryDTOToCategory;
    private final CategoryToCategoryDTO categoryToCategoryDTO;

    public CategoryController(CategoryServiceImpl categoryService, CategoryDTOToCategory categoryDTOToCategory, CategoryToCategoryDTO categoryToCategoryDTO) {
        this.categoryService = categoryService;
        this.categoryDTOToCategory = categoryDTOToCategory;
        this.categoryToCategoryDTO = categoryToCategoryDTO;
    }

    @RequestMapping("")
    @PreAuthorize("hasAnyAuthority('categories:read')")
    public ResponseEntity<List<CategoryDTO>> getAll(@RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(categoryToCategoryDTO.convertList(categoryService.findAll(page).getContent()), HttpStatus.OK);
    }

    @RequestMapping("/{name}")
    @PreAuthorize("hasAnyAuthority('categories:read')")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByName(@PathVariable String name, @RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(categoryToCategoryDTO.convertList(categoryService.findByName(page, name).getContent()), HttpStatus.OK);
    }

    // Create new category
    @PreAuthorize("hasAnyAuthority('categories:write')")
    @PostMapping("create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        categoryService.save(categoryDTOToCategory.convert(categoryDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Update category by id
    @PreAuthorize("hasAnyAuthority('categories:write')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Integer id) {
        categoryDTO.setId(id);
        categoryService.save(categoryDTOToCategory.convert(categoryDTO));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // Delete category by id
    @PreAuthorize("hasAnyAuthority('categories:write')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryDTO> deleteCategoryById(@PathVariable Integer id) {
        try {
            categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
