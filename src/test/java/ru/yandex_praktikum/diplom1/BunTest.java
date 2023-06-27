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
public class BunTest {
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;
    @Mock
    private Database database;

    @Before
    public void setUp(){
        Mockito.when(database.availableBuns()).thenReturn(new ArrayList<>(Arrays.asList(
                new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300)
        )));
    }
    @Test
    public void bunShouldBeCreated(){
        Bun bun = new Bun("black bun",100);

        Assert.assertEquals(database.availableBuns().get(0).getName(),bun.getName());
        Assert.assertEquals(database.availableBuns().get(0).getPrice(),bun.getPrice(),0.05);
    }

    @Test
    public void getNameNoParamsShouldReturnName(){
        Bun bun = new Bun("white bun",200);
        Assert.assertEquals("white bun",bun.getName());
    }

    @Test
    public void getPriceNoParamsShouldReturnPrice(){
        Bun bun = new Bun("white bun", 200);
        Assert.assertEquals(200,bun.getPrice(),0.05);
    }

    @Test
    public void createBunWithEmptyStringName(){
        Bun bun = new Bun("", 200);
        Assert.assertEquals(EMPTY_STRING,bun.getName());
    }

    @Test
    public void createBunWithNullStringName(){
        Bun bun = new Bun(null,200);
        Assert.assertEquals(NULL_STRING,bun.getName());
    }

    @Test
    public void createBunWithRussianName(){
        Bun bun = new Bun("Булка с кунжутом", 200);
        Assert.assertEquals("Булка с кунжутом", bun.getName());
    }

    @Test
    public void createBunWithChineseName(){
        Bun bun = new Bun("芝麻小圆面包",200);
        Assert.assertEquals("芝麻小圆面包", bun.getName());
    }

    @Test
    public void createBunWithNumbersInName(){
        Bun bun = new Bun("Булка с кунжутом 12345", 200);
        Assert.assertEquals("Булка с кунжутом 12345", bun.getName());
    }

    @Test
    public void createBunWithSpecialCharactersInName(){
        Bun bun = new Bun("&*^%$^$%$*!",200);
        Assert.assertEquals("&*^%$^$%$*!", bun.getName());
    }

    @Test
    public void createBunWithVeryLongName(){
        Bun bun = new Bun("Карл у Клары украл кораллы, а Клара у Карла украла кларнет",200);
        Assert.assertEquals("Карл у Клары украл кораллы, а Клара у Карла украла кларнет",bun.getName());
    }

    @Test
    public void createBunWithHyphenatedName(){
        Bun bun = new Bun("Булочка-с-кунжутом",200);
        Assert.assertEquals("Булочка-с-кунжутом", bun.getName());
    }

    @Test
    public void createBunWithMaximumPrice(){
        Bun bun = new Bun("Булочка с кунжутом", Float.MAX_VALUE);
        Assert.assertEquals(Float.MAX_VALUE,bun.getPrice(),0.05);
    }

    @Test
    public void createBunWithMinimumPrice(){
        Bun bun = new Bun("Булочка с кунжутом", Float.MIN_VALUE);
        Assert.assertEquals(Float.MIN_VALUE,bun.getPrice(),0.05);
    }

    @Test
    public void createBunWithZeroPrice(){
        Bun bun = new Bun("Булочка с кунжутом",0.0f);
        Assert.assertEquals(0.0f, bun.getPrice(),0.05);
    }

    @Test
    public void createBunWithNegativePrice(){
        Bun bun = new Bun("Булочка с кунжутом", -200f);
        Assert.assertEquals(-200f, bun.getPrice(),0.05);
    }

    @Test
    public void createBunWithMinimalFractionalPartPrice(){
        Bun bun = new Bun("Булочка с кунжутом", 0.01f);
        Assert.assertEquals(0.01f, bun.getPrice(),0.005);
    }

}
