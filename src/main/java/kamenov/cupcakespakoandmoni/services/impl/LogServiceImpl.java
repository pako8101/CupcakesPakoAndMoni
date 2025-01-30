package kamenov.cupcakespakoandmoni.services.impl;

import kamenov.cupcakespakoandmoni.models.CupCakeEntity;
import kamenov.cupcakespakoandmoni.models.LogEntity;
import kamenov.cupcakespakoandmoni.models.UserEntity;
import kamenov.cupcakespakoandmoni.models.service.LogServiceModel;
import kamenov.cupcakespakoandmoni.repos.LogRepository;
import kamenov.cupcakespakoandmoni.services.CupCakeService;
import kamenov.cupcakespakoandmoni.services.LogService;
import kamenov.cupcakespakoandmoni.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Instant.now;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final CupCakeService cupCakeService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private final Period deleteTime;


    public LogServiceImpl(LogRepository logRepository,
                          CupCakeService cupCakeService,
                          UserService userService,
                          ModelMapper modelMapper,
                          @Value("P35D") Period deleteTime) {
        this.logRepository = logRepository;
        this.cupCakeService = cupCakeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.deleteTime = deleteTime;
    }
    @Override
    public void createLog(String action, Long articleId) {

        CupCakeEntity cupCake = cupCakeService.findCupcakeById(articleId);

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        String username = authentication.getName();

        UserEntity userEnt = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setCupCake(cupCake)
                .setUserEntity(userEnt)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel =
                            modelMapper.map(logEntity, LogServiceModel.class);
                    logServiceModel.setCupcake(logEntity.getCupCake().getName())
                            .setUser(logEntity.getUserEntity().getUsername());

                    return logServiceModel;

                }).collect(Collectors.toList());
    }

    @Override
    public void deleteOldLogs() {
        Instant now = now();
        Instant deleteBefore = now.minus(deleteTime);
        LOGGER.info("Delete all articles before " + deleteBefore);
        logRepository.deleteOldLogs(deleteBefore);
        LOGGER.info("Old articles will be deleted");

    }

}
