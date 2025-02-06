package kamenov.cupcakespakoandmoni.services.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Negative;
import kamenov.cupcakespakoandmoni.exceptions.ObjectNotFoundException;
import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.dtos.CupCakeAddDto;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;
import kamenov.cupcakespakoandmoni.repos.CupCakeRepository;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CupCakeServiceImpl implements CupCakeService {
    private final ModelMapper modelMapper;
    private final CupCakeRepository cupCakeRepository;

    private List<CupCakeEntity> cupcakes = new ArrayList<>(List.of(
            new CupCakeEntity(5L, "Кето Кексче", "Вкусно и здравословно",
                    "/images/keto.jpg", 3.50, CupCakeTypeEnum.KETO, 5),
            new CupCakeEntity(6L, "Шоколадово Кексче", "С богат шоколадов вкус",
                    "/images/chocolate.jpg", 4.00, CupCakeTypeEnum.CHOCOLATE, 10),
            new CupCakeEntity(7L, "Плодово Кексче", "С пресни плодове",
                    "/images/fruit.jpg", 3.80, CupCakeTypeEnum.FRUITY, 10)
    ));

    public CupCakeServiceImpl(ModelMapper modelMapper, CupCakeRepository cupCakeRepository) {
        this.modelMapper = modelMapper;
        this.cupCakeRepository = cupCakeRepository;
    }

    @Override
    public List<CupCakeEntity> getAllCupcakes() {
        return cupCakeRepository.findAll().stream().toList();
    }

    @Override
    public List<CupCakeEntity> getCupcakesByType(CupCakeTypeEnum type) {
        return cupCakeRepository.findAllByType(type);
    }

    @Override
    public CupCakeEntity getCupcakeById(Long id) {
        return cupCakeRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public void addCupcake(CupCakeAddDto form) {
        // long newId = cupcakes.stream().mapToLong(CupCakeEntity::getId).max().orElse(0L) + 1;
        CupCakeEntity cupCake = modelMapper.map(form, CupCakeEntity.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUsername = authentication.getName();
        if (currentUsername == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        cupCake.setId(null);

        cupCake.setQuantity(form.getQuantity());
        cupCake.setDescription(form.getDescription());
        cupCake.setName(form.getName());
        cupCake.setPrice(form.getPrice());
        cupCake.setType(form.getType());
        cupCake.setImage(form.getImage());
//         cupCake = new CupCakeEntity(
//                form.getId(),
//                form.getName(),
//                form.getDescription(),
//                form.getImage(),
//                form.getPrice(),
//                form.getType(),
//                form.getQuantity());
        if (cupCake.getId() != null) {
            Optional<CupCakeEntity> existingCupcake = cupCakeRepository.findById(form.getId());
            if (existingCupcake.isPresent()) {
                CupCakeEntity updatedCupCake = existingCupcake.get();
                updatedCupCake.setQuantity(form.getQuantity());
                updatedCupCake.setDescription(form.getDescription());
                updatedCupCake.setName(form.getName());
                updatedCupCake.setPrice(form.getPrice());
                updatedCupCake.setType(form.getType());
                updatedCupCake.setImage(form.getImage());
                cupCakeRepository.save(updatedCupCake);
                return;
            }
        }

        cupcakes.add(cupCake);
        cupCakeRepository.save(cupCake);
    }

    @Override
    public void updateStock(Long id, int quantity) {
        CupCakeEntity cupcake = cupCakeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (cupcake != null && cupcake.getQuantity() >= quantity) {
            cupcake.setQuantity(cupcake.getQuantity() - quantity);
            cupCakeRepository.saveAndFlush(cupcake);
        }
    }

    @Override
    public CupCakeEntity findCupcakeById(Long id) {
        return cupCakeRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("No such cupcake with " + id, id));
    }
}
