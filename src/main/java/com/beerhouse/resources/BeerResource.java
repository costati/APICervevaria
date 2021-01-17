package com.beerhouse.resources;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/beer")
public class BeerResource {

    @Autowired
    BeerService beerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Beer>> findAll() {
        return ResponseEntity.ok(beerService.finByAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beer> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(beerService.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Beer beer) {
        beerService.save(beer);

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(beer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beer> update(@RequestBody Beer beer) {
        return ResponseEntity.ok(beerService.save(beer));
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Beer> updatePartial(@PathVariable("id") final Long id,
                                              @RequestBody Beer beer) {
        return ResponseEntity.ok( beerService.updatePartial(id,beer));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id){
        beerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
