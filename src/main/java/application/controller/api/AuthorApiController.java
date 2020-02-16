package application.controller.api;

import application.data.model.Author;
import application.data.model.AuthorProduct;
import application.data.model.Product;
import application.data.service.AuthorService;
import application.data.service.ProductAuthorService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/author")
public class AuthorApiController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductAuthorService productAuthorService;
    @Autowired
    AuthorService authorService;
    private String[] images  = {
            "Nguyễn Văn A",
            "Lê Văn C",
            "Trần Văn B",
            "Hoàn Văn D",
            "Phí Văn E",
    };
    private String[] profiles  = {
            "Nguyễn Văn A:là nhà văn người Viêt Nam ",
            "Lê Văn C:là nhà văn người Viêt Nam ",
            "Trần Văn B:là nhà văn người Viêt Nam ",
            "Hoàn Văn D:là nhà văn người Viêt Nam ",
            "Phí Văn E:là nhà văn người Viêt Nam ",
    };
    @GetMapping("/fake")
    public BaseApiResult fakeAuthor(){
        BaseApiResult result = new BaseApiResult();
        try {
            List<Author> authorList = new ArrayList<>();
            List<AuthorProduct> authorProductList = new ArrayList<>();
            Random random = new Random();
            for (int i = 1; i < 40; i++) {
                Author author = new Author();
                author.setBirthDay(new Date());
                int j = random.nextInt(images.length);
                author.setName(images[j]);
                author.setProfile(profiles[j]);
                authorList.add(author);
            }
            authorService.addNewListAuthor(authorList);
            result.setSuccess(true);
            result.setMessage("Success");
        }catch (Exception e){
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        }
       return result;
    }
}
