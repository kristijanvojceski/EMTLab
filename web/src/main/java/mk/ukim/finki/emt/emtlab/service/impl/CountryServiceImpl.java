package mk.ukim.finki.emt.emtlab.service.impl;

import mk.ukim.finki.emt.emtlab.models.Country;
import mk.ukim.finki.emt.emtlab.models.dto.CountryDto;
import mk.ukim.finki.emt.emtlab.repository.CountryRepository;
import mk.ukim.finki.emt.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> addNewCountry(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(),countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
