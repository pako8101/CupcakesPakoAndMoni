package kamenov.cupcakespakoandmoni.repos;

import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.enums.CupCakeTypeEnum;
import kamenov.cupcakespakoandmoni.web.CupCakeController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CupCakeRepository extends JpaRepository<CupCakeEntity,Long> {

    List<CupCakeEntity>findAllByType(CupCakeTypeEnum type);
}
