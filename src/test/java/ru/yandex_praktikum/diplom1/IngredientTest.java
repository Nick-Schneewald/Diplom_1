package ru.yandex_praktikum.diplom1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;


@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {
    @Mock
    private Database database;

    @Before
    public void setUp(){
        Mockito.when(database.availableIngredients()).thenReturn(new ArrayList<>(Arrays.asList(
                new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                new Ingredient(IngredientType.FILLING, "dinosaur", 200),
                new Ingredient(IngredientType.FILLING, "sausage", 300)
        )));
    }
    @Test
    public void ingredientShouldBeCreated(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"hot sauce",100);

        Assert.assertEquals(database.availableIngredients().get(0).getType(),ingredient.getType());
        Assert.assertEquals(database.availableIngredients().get(0).getName(),ingredient.getName());
        Assert.assertEquals(database.availableIngredients().get(0).getPrice(),ingredient.getPrice(), 0.05);
    }

    @Test
    public void getTypeNoParamsShouldReturnType(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"hot sauce",100);
        Assert.assertEquals(IngredientType.SAUCE,ingredient.getType());
    }

    @Test
    public void getNameNoParamsShouldReturnName(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"hot sauce",100);
        Assert.assertEquals("hot sauce",ingredient.getName());
    }

    @Test
    public void getPriceNoParamsShouldReturnPrice(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"hot sauce",100);
        Assert.assertEquals(100,ingredient.getPrice(),0.05);
    }
}
