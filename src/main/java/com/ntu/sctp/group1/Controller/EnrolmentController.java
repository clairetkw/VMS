package com.ntu.sctp.group1.Controller;

import com.ntu.sctp.group1.DataTransferObject.EnrolDto;
import com.ntu.sctp.group1.DataTransferObject.EnrolEditDto;
import com.ntu.sctp.group1.Exceptions.NoEnrolmentFoundExceptions;
import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.Service.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/enrolment")
public class EnrolmentController {

    @Autowired 
    EnrolmentService enrolmentService;

    record Status(String msg, boolean success){};

    @GetMapping
        public ResponseEntity<?> getAllEnrolments() {
            try {
                return ResponseEntity.ok().body(enrolmentService.getAllEnrolments());
            } catch (NoEnrolmentFoundExceptions ex) {
              ex.printStackTrace();
              return ResponseEntity.notFound().build();
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
            } 
        }

        @PostMapping("/newenrolment")
    public ResponseEntity<?> createEnrolment(@RequestBody EnrolDto enrolDto) {
        try {
            return ResponseEntity.ok().body(enrolmentService.createEnrolment(enrolDto));
        } catch (NoEnrolmentFoundExceptions ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
        }
    }

    @PutMapping ("/update")
        public ResponseEntity<?> updateEnrolment(@RequestBody EnrolEditDto enrolEditDto) {
            try {
                return ResponseEntity.ok().body(enrolmentService.updateEnrolment(enrolEditDto));
            } catch (NoEnrolmentFoundExceptions ex) {
                ex.printStackTrace();
                return ResponseEntity.notFound().build();
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(new Status(ex.getMessage(), false));
            }
        }

    @PostMapping("/volunteers")
    public ResponseEntity<?> addVolunteer(@RequestParam int volunteer_id, @RequestParam int program_id){
        try{
            enrolmentService.addVolunteer(volunteer_id, program_id);
            return ResponseEntity.ok().body("volunteer added successful");
        }catch (NoVolunteerFoundExceptions | NoEnrolmentFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(),false));
        }
    }

    @GetMapping ("/volunteers")
    public ResponseEntity<?> getAllVolunteer(@RequestParam int program_id){
        try{
            return ResponseEntity.ok().body(enrolmentService.getAllVolunteer(program_id));
        }catch (NoEnrolmentFoundExceptions ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body(new Status(ex.getMessage(),false));
        }
    }
}
