package com.example.eksamenbackend.Races.repository;

import com.example.eksamenbackend.Races.model.ParticipantModel;
import com.example.eksamenbackend.Races.model.RaceModel;
import com.example.eksamenbackend.sailboat.model.SailboatModel;
import com.example.eksamenbackend.sailboat.repository.SailboatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ParticipantRepositoryTest {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    SailboatRepository sailboatRepository;

    @Autowired
    RaceRepository raceRepository;


    @Test
    void findAllParticipants() {
        SailboatModel sailboatModel = new SailboatModel();
        sailboatModel.setName("Test");
        sailboatModel.setType(SailboatModel.SailboatType.FOOT_40);
        sailboatRepository.save(sailboatModel);
        RaceModel raceModel = new RaceModel();
        raceModel.setRaceType(SailboatModel.SailboatType.FOOT_40);
        raceRepository.save(raceModel);
        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setBoatName(sailboatModel.getName());
        participantModel.setBoatId(sailboatModel.getId());
        participantModel.setRace(raceModel);
        participantRepository.save(participantModel);

        List<ParticipantModel> participantModels = participantRepository.findAll();

        assertEquals(1, participantModels.size());

    }

    @Test
    void createParticipant() {
        SailboatModel sailboatModel = new SailboatModel();
        sailboatModel.setName("Test");
        sailboatModel.setType(SailboatModel.SailboatType.FOOT_40);
        sailboatRepository.save(sailboatModel);

        RaceModel raceModel = new RaceModel();
        raceModel.setRaceType(SailboatModel.SailboatType.FOOT_40);
        raceRepository.save(raceModel);

        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setBoatName(sailboatModel.getName());
        participantModel.setBoatId(sailboatModel.getId());
        participantModel.setRace(raceModel);
        participantRepository.save(participantModel);
        System.out.println(participantModel);

        ParticipantModel test = participantRepository.findById(participantModel.getBoatId()).get();
        System.out.println(participantModel.getBoatId());
        System.out.println(test.getBoatName());

        assertEquals("Test", test.getBoatName());
    }

    @Test
    void updateParticipant() {
        SailboatModel sailboatModel = new SailboatModel();
        sailboatModel.setName("Test");
        sailboatModel.setType(SailboatModel.SailboatType.FOOT_40);
        sailboatRepository.save(sailboatModel);

        RaceModel raceModel = new RaceModel();
        raceModel.setRaceType(SailboatModel.SailboatType.FOOT_40);
        raceRepository.save(raceModel);

        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setBoatName(sailboatModel.getName());
        participantModel.setBoatId(sailboatModel.getId());
        participantModel.setRace(raceModel);
        participantRepository.save(participantModel);
        System.out.println(participantModel);

        sailboatModel.setName("Test2");
        sailboatModel.setType(SailboatModel.SailboatType.FOOT_25);
        sailboatRepository.save(sailboatModel);

        participantModel.setBoatName(sailboatModel.getName());
        participantModel.setBoatId(sailboatModel.getId());
        participantModel.setRace(raceModel);
        participantRepository.save(participantModel);
        System.out.println(participantModel);

        ParticipantModel test = participantRepository.findById(participantModel.getBoatId()).get();
        System.out.println(participantModel.getBoatId());
        System.out.println(test.getBoatName());

        assertEquals("Test2", test.getBoatName());
    }

    @Test
    void deleteParticipant() {
        SailboatModel sailboatModel = new SailboatModel();
        sailboatModel.setName("Test");
        sailboatModel.setType(SailboatModel.SailboatType.FOOT_40);
        sailboatRepository.save(sailboatModel);

        RaceModel raceModel = new RaceModel();
        raceModel.setRaceType(SailboatModel.SailboatType.FOOT_40);
        raceRepository.save(raceModel);

        ParticipantModel participantModel = new ParticipantModel();
        participantModel.setBoatName(sailboatModel.getName());
        participantModel.setBoatId(sailboatModel.getId());
        participantModel.setRace(raceModel);
        participantRepository.save(participantModel);
        System.out.println(participantModel);

        participantRepository.deleteById(participantModel.getId());
        List<ParticipantModel> participantModels = participantRepository.findAll();
        assertEquals(0, participantModels.size());
    }

    @Test
    void allBoatsFromParticipant() {
        SailboatModel sailboatModel = new SailboatModel();
        sailboatModel.setName("Test");
        sailboatModel.setType(SailboatModel.SailboatType.FOOT_40);
        sailboatRepository.save(sailboatModel);

        List<SailboatModel> models = sailboatRepository.findBoatModelsById(sailboatModel.getId());

        assertEquals(1, models.size());
        assertEquals(sailboatModel.getName(), models.get(0).getName());
        assertEquals(sailboatModel.getType(), models.get(0).getType());
    }


}