package kamenov.cupcakespakoandmoni.services;

import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.dtos.CupCakeAddDto;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;

import java.util.List;

public interface CupCakeService {
    List<CupCakeEntity> getAllCupcakes();



    List<CupCakeEntity> getCupcakesByType(CupCakeTypeEnum type);

    CupCakeEntity getCupcakeById(Long id);

    void addCupcake(CupCakeAddDto form);

    void updateStock(Long id, int quantity);

    CupCakeEntity findCupcakeById(Long id);
}
