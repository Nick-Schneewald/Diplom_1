package ru.yandex_praktikum.diplom1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunParamTest {
    private static final String EMPTY_STRING = "";
    private static final String NULL_STRING = null;
    private static final String DEFAULT_NAME = "white bun";

    private static final float DEFAULT_PRICE = 200f;

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public float price;

    @Parameterized.Parameter(2)
    public String expectedName;

    @Parameterized.Parameter(3)
    public float expectedPrice;

    @Parameterized.Parameters(name="{index} Название булочки:{0}, Стоимость булочки:{1}")
    public static Collection<Object[]> getData(){
        return Arrays.asList( new Object[][]{
                {"white bun", 200, "white bun", 200},
                {"black bun", 100, "black bun", 100},
                {"", 200, EMPTY_STRING, 200},
                {null, 200, NULL_STRING, 200},
                {"Булка с кунжутом", 200, "Булка с кунжутом", 200},
                {"芝麻小圆面包",200,"芝麻小圆面包",200},
                {"Булка с кунжутом 12345", 200,"Булка с кунжутом 12345", 200},
                {"&*^%$^$%$*!",200,"&*^%$^$%$*!",200},
                {"Карл у Клары украл кораллы, а Клара у Карла украла кларнет",200,"Карл у Клары украл кораллы, а Клара у Карла украла кларнет",200},
                {"Булочка-с-кунжутом",200,"Булочка-с-кунжутом",200},
                {"Булочка с кунжутом", Float.MIN_VALUE,"Булочка с кунжутом", Float.MIN_VALUE},
                {"Булочка с кунжутом", Float.MAX_VALUE,"Булочка с кунжутом", Float.MAX_VALUE},
                {"Булочка с кунжутом",0.0f,"Булочка с кунжутом",0.0f},
                {"Булочка с кунжутом", -200f,"Булочка с кунжутом", -200f},
                {"Булочка с кунжутом", 0.01f,"Булочка с кунжутом", 0.01f}
                }
        );
    }

    @Test
    public void generalizedTest(){
        Bun bun = new Bun(name, price);

        Assert.assertEquals(expectedName,bun.getName());
        Assert.assertEquals(expectedPrice, bun.getPrice(),0.05);
    }
}
