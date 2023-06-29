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
}
