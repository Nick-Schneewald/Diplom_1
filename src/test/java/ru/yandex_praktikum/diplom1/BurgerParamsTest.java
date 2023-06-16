package ru.yandex_praktikum.diplom1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerParamsTest {

    @Parameterized.Parameter(0)
    public Bun bun;

    @Parameterized.Parameter(1)
    public List<Ingredient> ingredients;

    @Parameterized.Parameter(2)
    public float expectedPrice;

    @Parameterized.Parameter(3)
    public String expectedReceipt;

    Database database = Mockito.mock(Database.class);
    @Parameterized.Parameters(name = "{index} Вычисляет стоимость и рецепт бургера {0} - булочка, {1} - начинка, {2} - стоимость, {3} - чек")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {
                        new Bun("black bun", 100),
                        new ArrayList<>(Arrays.asList(
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200)
                        )),
                        700,
                        "(==== black bun ====)\r\n" +
                                "= sauce sour cream =\r\n" +
                                "= filling cutlet =\r\n" +
                                "= filling dinosaur =\r\n" +
                                "(==== black bun ====)\r\n" +
                                "\r\nPrice: 700,000000\r\n"
                },
                {
                        new Bun("white bun",200),
                        new ArrayList<>(Arrays.asList(
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                                new Ingredient(IngredientType.FILLING, "sausage", 300)
                        )),
                        1100,
                        "(==== white bun ====)\r\n" +
                                "= sauce chili sauce =\r\n" +
                                "= filling cutlet =\r\n" +
                                "= filling sausage =\r\n" +
                                "(==== white bun ====)\r\n" +
                                "\r\nPrice: 1100,000000\r\n"
                }
        });
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        Mockito.when(database.availableBuns()).thenReturn(new ArrayList<>(Arrays.asList(
                new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300)
        )));
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
    public void getBurgerPrice() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.05);
    }

    @Test
    public void getBurgerReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        Assert.assertEquals(expectedReceipt,burger.getReceipt());
    }

}
