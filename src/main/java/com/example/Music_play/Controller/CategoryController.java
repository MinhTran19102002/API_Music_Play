package com.example.Music_play.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Music_play.mapper.CategoryMapper;
import com.example.Music_play.model.Category;
import com.example.Music_play.modelDTO.CategoryDTO;
import com.example.Music_play.modelMessage.CategoryMessage;
import com.example.Music_play.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    private final Cloudinary cloudinary;

    @Autowired
    public CategoryController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @PostMapping(value = "/all")
    public List<CategoryDTO> getAllCategory(){

        return categoryMapper.getListCategory(categoryRepository.findAll());
    }

    @PostMapping(value = "/create")
    public CategoryMessage createCategory(@RequestParam String name, @RequestParam("file") MultipartFile image, @RequestParam String description) throws IOException {
        CategoryMessage categoryMessage = new CategoryMessage();
        Category category = new Category();
        String linkImage = "";

        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            linkImage = uploadResult.get("secure_url").toString();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        category.setName(name);
        category.setImage(linkImage);
        category.setDescription(description);

        try{
            categoryRepository.save(category);
            categoryMessage.setCategory(category);
            categoryMessage.setMessage("You have successfully created a category!");
            return categoryMessage;
        }catch (Exception e){
            categoryMessage.setCategory(null);
            categoryMessage.setMessage("You have failed to create a category");
            return categoryMessage;
        }
    }
}
