package beerproject.msscbeerservice.web.mapper;

import beerproject.msscbeerservice.web.domain.Beer;
import beerproject.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDto beerDto);

    BeerDto beerToBeerDto(Beer beer);
}
