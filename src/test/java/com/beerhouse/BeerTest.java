package com.beerhouse;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.service.BeerService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackageClasses = Application.class)
public class BeerTest {

    @Autowired
    BeerService beerService;


    @Test
    public void create(){

        Beer beer = new Beer();
        beer.setName("Teste create");
        beer.setPrice(10.50);
        beer.setIngredients("cevada");
        beer.setCategory("malte");
        beer.setAlcoholContent("sim");

        beer = beerService.save(beer);

        Assertions.assertThat(beer)
                .isNotNull();

        beerService.delete(beer.getId());

    }

    @Test
    public void update(){

        String name = "update";

        Beer beer = new Beer();
        beer.setName("Teste ");
        beer.setPrice(10.50);
        beer.setIngredients("cevada");
        beer.setCategory("malte");
        beer.setAlcoholContent("sim");

        beer = beerService.save(beer);

        beer.setName(name);

        beer = beerService.save(beer);

        Assertions.assertThat(beer.getName())
                .isEqualTo(name);

        beerService.delete(beer.getId());
    }

    @Test
    public void delete(){

        Beer beer = new Beer();
        beer.setName("Teste delete");
        beer.setPrice(10.50);
        beer.setIngredients("cevada");
        beer.setCategory("malte");
        beer.setAlcoholContent("sim");

        beer = beerService.save(beer);

        beerService.delete(beer.getId());

        Assertions.assertThat(beerService.findById(beer.getId()))
                .isNull();
    }

    @Test
    public void updatePartial(){

        Beer beerAlt = new Beer();
        beerAlt.setName("update partial");

        Beer beer = new Beer();
        beer.setName("Teste update partial");
        beer.setPrice(10.50);
        beer.setIngredients("cevada");
        beer.setCategory("malte");
        beer.setAlcoholContent("sim");

        beer = beerService.save(beer);

        beer = beerService.updatePartial(beer.getId(), beerAlt);

        Assertions.assertThat(beer.getName())
                .isEqualTo(beerAlt.getName());

        beerService.delete(beer.getId());
    }

    @Test
    public void findById(){

        Beer beer = new Beer();
        beer.setName("Teste findById");
        beer.setPrice(10.50);
        beer.setIngredients("cevada");
        beer.setCategory("malte");
        beer.setAlcoholContent("sim");

        beer = beerService.save(beer);

        Beer beer1 = beerService.findById(beer.getId());

        Assertions.assertThat(beer.getId())
                .isEqualTo(beer1.getId());

        beerService.delete(beer.getId());
    }

    @Test
    public void findAll(){

        Beer beer = new Beer();
        beer.setName("Teste findAll");
        beer.setPrice(10.50);
        beer.setIngredients("cevada");
        beer.setCategory("malte");
        beer.setAlcoholContent("sim");

        beer = beerService.save(beer);

        Assertions.assertThat(beerService.finByAll().size()).isNotZero() ;

        beerService.delete(beer.getId());
    }

}
