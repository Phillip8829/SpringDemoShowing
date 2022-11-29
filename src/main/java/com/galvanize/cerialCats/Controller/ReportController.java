package com.galvanize.cerialCats.Controller;


import com.galvanize.cerialCats.Model.Report;
import com.galvanize.cerialCats.Repo.ReportRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/Report")
@CrossOrigin()
public class ReportController {

    ReportRepo reportRepo;


    //Constructor
    ReportController(ReportRepo reportRepo)
    {
        this.reportRepo = reportRepo;
    }


    //Post
    @PostMapping("")
    ResponseEntity<Report> postNewReport(@RequestBody Report body)
    {
        return new ResponseEntity<>(reportRepo.save(body), HttpStatus.OK);
    }

    //Get All
    @GetMapping("")
    List<Report> getAllReports()
    {
        return reportRepo.findAll();
    }

    //Get One
    @GetMapping("{id}")
    ResponseEntity<Report> getOneReport(@PathVariable Long id)
    {
        if (reportRepo.findById(id).isPresent())
        {
            return new ResponseEntity<>(reportRepo.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //Delete
    @DeleteMapping("{id}")
    ResponseEntity<Report> deleteOneReport(@PathVariable Long id)
    {
        if (reportRepo.findById(id).isPresent())
        {
            reportRepo.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    //Patch / Update
    @PatchMapping("{id}")
    Report patchOneReport(@PathVariable Long id, @RequestBody Map<String, Object> body)
    {
        List<String> cars = new ArrayList<String>();

        //ID IS PRESENT TO BE UPDATED
        if (reportRepo.findById(id).isPresent())
        {
            Report holder = reportRepo.findById(id).get();
            for (Map.Entry<String,Object> entry: body.entrySet())
            {
                //Get Key Value and if it matches then put new Value In that Key
                switch (entry.getKey()) {

                    case "dateTime" -> {
                        if (!entry.getValue().toString().contains("T")) {
                            entry.setValue(entry.getValue().toString().replace(" ", "T"));
                        }
                        holder.setDateTime(LocalDateTime.parse(entry.getValue().toString()));
                    }
                    case "location" -> holder.setLocation(entry.getValue().toString());
                    case "eventType" -> holder.setEventType(Boolean.parseBoolean(entry.getValue().toString()));
                    case "harm" -> holder.setHarm(Boolean.parseBoolean(entry.getValue().toString()));

                    case "individualsInvolved" -> {
                        List<String> listo = (List<String>) entry.getValue();
                        holder.setIndividualsInvolved(listo);
                    }

                    case "eventCategory" -> {
                        List<String> listo = (List<String>) entry.getValue();
                        holder.setEventCategory(listo);
                    }

                    case "incidentEffect" -> holder.setIncidentEffect(Boolean.parseBoolean(entry.getValue().toString()));
                    case "witnessName" -> {
                        List<String> listo = (List<String>) entry.getValue();
                        holder.setEventCategory(listo);
                    }
                    case "witnessTelephone" -> {
                        List<String> listo = (List<String>) entry.getValue();
                        holder.setEventCategory(listo);
                    }
                    case "departmentInvolved" -> {
                        List<String> listo = (List<String>) entry.getValue();
                        holder.setDepartmentsInvolved(listo);
                    }

                    case "description" -> holder.setDescription(entry.getValue().toString());
                    case "action" -> holder.setActions(entry.getValue().toString());
                    case "patientName" -> holder.setPatientName(entry.getValue().toString());
                    case "patientSSN" -> holder.setPatientSSN(entry.getValue().toString());
                    case "patientPhone" -> holder.setPatientPhone(entry.getValue().toString());
                    case "patientAddress" -> holder.setPatientAddress(entry.getValue().toString());
                }
                return reportRepo.save(holder);
            }

        }
        //Item Was Not Found Or Existed
        return null;
    }


}
