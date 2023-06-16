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
public class BurgerTest {
    @Mock
    Database database;
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

        Mockito.when(database.availableBuns()).thenReturn(new ArrayList<>(Arrays.asList(
                new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300)
        )));
    }

    @Test
    public void burgerShouldBeCreatedWithDefaultConstructor(){
        Burger burger = new Burger();
        Assert.assertNotNull(burger);
    }

    @Test
    public void setBunBunInstanceBurgerGotBun(){
        Burger burger = new Burger();
        burger.setBuns(database.availableBuns().get(0));
        Assert.assertNotNull(burger.bun);
    }

    @Test
    public void addIngredientIngredientInstanceIngredientsListIsNotEmpty(){
        Burger burger = new Burger();
        burger.addIngredient(database.availableIngredients().get(0));
        Assert.assertNotEquals(0,burger.ingredients.size());
    }

    @Test
    public void removeIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(database.availableIngredients().get(0));
        burger.removeIngredient(0);
        Assert.assertEquals(0,burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientFromEmptyIngredientList(){
        Burger burger = new Burger();
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(database.availableIngredients().get(0));
        burger.addIngredient(database.availableIngredients().get(3));
        burger.addIngredient(database.availableIngredients().get(5));

        burger.moveIngredient(1,2);
        Assert.assertEquals("cutlet",burger.ingredients.get(2).name);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientToOutOfSizePosition(){
        Burger burger = new Burger();
        burger.addIngredient(database.availableIngredients().get(0));
        burger.addIngredient(database.availableIngredients().get(3));
        burger.addIngredient(database.availableIngredients().get(5));

        burger.moveIngredient(1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientToNegativePosition(){
        Burger burger = new Burger();
        burger.addIngredient(database.availableIngredients().get(0));
        burger.addIngredient(database.availableIngredients().get(3));
        burger.addIngredient(database.availableIngredients().get(5));

        burger.moveIngredient(1, -1);
    }
}
