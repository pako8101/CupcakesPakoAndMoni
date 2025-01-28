package kamenov.cupcakespakoandmoni.services.impl;

import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.dtos.CupCakeAddDto;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;
import kamenov.cupcakespakoandmoni.repos.CupCakeRepository;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CupCakeServiceImpl implements CupCakeService {
    private final CupCakeRepository cupCakeRepository;

    private List<CupCakeEntity> cupcakes = new ArrayList<>(List.of(
            new CupCakeEntity( "Кето Кексче", "Вкусно и здравословно",
                    "/images/keto.jpg",3.50, CupCakeTypeEnum.KETO, 5),
            new CupCakeEntity( "Шоколадово Кексче", "С богат шоколадов вкус",
                    "/images/chocolate.jpg", 4.00,CupCakeTypeEnum.CHOCOLATE,10),
            new CupCakeEntity( "Плодово Кексче", "С пресни плодове",
                    "/images/fruit.jpg", 3.80,CupCakeTypeEnum.FRUITY,10)
    ));

    public CupCakeServiceImpl(CupCakeRepository cupCakeRepository) {
        this.cupCakeRepository = cupCakeRepository;
    }

    @Override
    public List<CupCakeEntity> getAllCupcakes() {
        return cupcakes;
    }
@Override
    public List<CupCakeEntity> getCupcakesByType(CupCakeTypeEnum type) {
        return cupCakeRepository.findAllByType(type);
    }
@Override
    public CupCakeEntity getCupcakeById(Long id) {
        return cupCakeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
@Override
    public void addCupcake(CupCakeAddDto form) {
        long newId = cupcakes.stream().mapToLong(CupCakeEntity::getId).max().orElse(0L) + 1;
        CupCakeEntity newCupcake = new CupCakeEntity( form.getName(), form.getDescription(),
                form.getImage(), form.getPrice(), form.getType(),form.getQuantity());
        cupcakes.add(newCupcake);
    }
}
