package pl.merkkarol.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.merkkarol.model.CategoriesOfExpense;
import pl.merkkarol.model.CategoriesOfExpenseRepository;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("integration")
class CategoriesServiceTest {

    @Autowired
    private CategoriesOfExpenseRepository repository;


    @Test
    @DisplayName("Should throw IllegalStateException when Category has empty name")
    void createCategory_but_Category_name_is_empty_throw_IllegalStateException(){
        //given
        CategoriesOfExpense categories = new CategoriesOfExpense("");
        var categoriesRepository = mock(CategoriesOfExpenseRepository.class);
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);
        //when + then
        var exception = catchThrowable(() -> categoriesService.createCategory(categories));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("name can not be empty");
       }
       @Test
       void createCategory_but_Category_with_given_name_already_exists_return_that_category(){
        //given
        CategoriesOfExpense category = new CategoriesOfExpense("wOda");
        repository.save(category);
        CategoriesService service = new CategoriesService(repository);
        CategoriesOfExpense toTest = new CategoriesOfExpense("woda");
        //when + then
        assertThat(service.createCategory(toTest).equals(repository.getByCategoryName(category.getCategoryName())));
    }
        @Test
    void createCategory_OK(){
        //given
        CategoriesOfExpense category = new CategoriesOfExpense("szkoła");
        //when
        int after = repository.findAll().size();
        repository.save(category);
        int before = repository.findAll().size();
        //then
        assertThat(before == after + 1);
        assertThat(repository.existsByCategoryName(category.getCategoryName())).isTrue();
    }
    @Test
    void existsByCategoryName_but_given_string_is_empty_Should_throw_IllegalStateException(){
        //given
     String emptyName = "";
     CategoriesOfExpenseRepository repositoryCategories = mock(CategoriesOfExpenseRepository.class);
     CategoriesService service = new CategoriesService(repositoryCategories);
        //when
     var exception = catchThrowable(() -> service.existsByCategoryName(emptyName));
        //then
     assertThat(exception).isInstanceOf(IllegalStateException.class).hasMessageContaining("name can not be empty");
    }
}