package com.beerhouse.domain.service;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.exception.GenericErrorException;
import com.beerhouse.domain.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BeerService {

    @Autowired
    BeerRepository beerRepository;

    public List<Beer> finByAll(){
        return beerRepository.findAll();
    }

    public Beer save (Beer beer){
        return beerRepository.save(beer);
    }

    public Beer findById(Long id){

        if(!beerRepository.exists(id)){
            throw new GenericErrorException("Cerveja não encontrada");
        }

        return beerRepository.findOne(id);
    }

    public void delete(Long id){

        if(!beerRepository.exists(id)){
            throw new GenericErrorException("Cerveja não encontrada");
        }

        beerRepository.delete(id);
    }

    public Beer updatePartial(Long id, Beer beer){

        Beer beerBase = findById(id);

        if(Objects.nonNull(beer.getName()) && !beer.getName().isEmpty()){
            beerBase.setName(beer.getName());
        }

        if(Objects.nonNull(beer.getAlcoholContent()) && !beer.getAlcoholContent().isEmpty()){
            beerBase.setAlcoholContent(beer.getAlcoholContent());
        }

        if(Objects.nonNull(beer.getCategory()) && !beer.getCategory().isEmpty()){
            beerBase.setCategory(beer.getCategory());
        }

        if(Objects.nonNull(beer.getIngredients()) && !beer.getIngredients().isEmpty()){
            beerBase.setIngredients(beer.getIngredients());
        }

        if(beer.getPrice() > 0 ){
            beerBase.setPrice(beer.getPrice());
        }

        return beerRepository.save(beerBase);

    }



}
