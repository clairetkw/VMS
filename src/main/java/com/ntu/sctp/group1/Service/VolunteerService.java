package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    
    VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() throws NoVolunteerFoundExceptions {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers.isEmpty()) {
             throw new NoVolunteerFoundExceptions("No Volunteer Found!");
        } else {
            return volunteers;
        }
    }

    public Volunteer getVolunteerById(int id) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            return volunteer.get();
        } else {
            throw new NoVolunteerFoundExceptions ("Volunteer not found with ID: " + id);
        }
    }

   
   
    public List<Volunteer> searchByParams (Map <String, String> params) throws NoVolunteerFoundExceptions {
        List<Volunteer> volunteers = volunteerRepository.findAll();
        if (volunteers.size() == 0) {
            throw new NoVolunteerFoundExceptions("No volunteers meeting this criteria found");
        }
        List<Volunteer> filteredList = new ArrayList<>();

        if (params.containsKey("experience")) {
            filteredList = volunteers.stream()
                            .filter((volunteer) -> volunteer.getEducation().equalsIgnoreCase(params.get("education")) ||
                                                   volunteer.getLanguage().equalsIgnoreCase(params.get("language")) ||
                                                   volunteer.getPastExperience().contains(params.get("experience")))
                            .toList();
        } else {
            filteredList = volunteers.stream()
                            .filter((volunteer) -> volunteer.getEducation().equalsIgnoreCase(params.get("education")) ||
                                                   volunteer.getLanguage().equalsIgnoreCase(params.get("language")))
                            .toList();
        }

        if(filteredList.size() == 0) {
            throw new NoVolunteerFoundExceptions("No results found!");
        }

        return filteredList;
    }

    public Volunteer createVolunteer(Volunteer newVolunteer) throws NoVolunteerFoundExceptions {
        if (newVolunteer.getName().isEmpty()) {
            throw new NoVolunteerFoundExceptions("Volunteer's name and email cannot be empty");
        }
        return volunteerRepository.save(newVolunteer);
    }

    public Volunteer updateVolunteer(int id, Volunteer updatedVolunteer) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            Volunteer existingVolunteer = volunteer.get();
            existingVolunteer.setName(updatedVolunteer.getName());
            existingVolunteer.setEmail(updatedVolunteer.getEmail());
            return volunteerRepository.save(existingVolunteer);
        } else {
            throw new NoVolunteerFoundExceptions("Volunteer not found with ID: " + id);
        }
    }

    public void deleteVolunteer(int id) throws NoVolunteerFoundExceptions {
    Optional<Volunteer> volunteer = volunteerRepository.findById(id);
        if (volunteer.isPresent()) {
            Volunteer existingVolunteer = volunteer.get();
            volunteerRepository.delete(existingVolunteer);
        } else {
            throw new NoVolunteerFoundExceptions("Volunteer not found with ID: " + id);
        }
    }
}
