package com.galvanize.cerialCats.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    LocalDateTime dateTime;

    String location;

    Boolean eventType;

    Boolean harm;

    @ElementCollection
    List<String> individualsInvolved = new ArrayList<>();

    @ElementCollection
    List<String> eventCategory = new ArrayList<>();

    boolean incidentEffect;

    @ElementCollection
    List<String> witnessName;

    @ElementCollection
    List<String> witnessTelephone;

    @ElementCollection
    List<String> departmentsInvolved = new ArrayList<>();

    @Lob
    String description;

    @Lob
    String actions;

    String patientName;

    // User has privileged access to read SSN
    String patientSSN;

    String patientPhone;

    String patientAddress;

    //Getter & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getEventType() {
        return eventType;
    }

    public void setEventType(Boolean eventType) {
        this.eventType = eventType;
    }

    public Boolean getHarm() {
        return harm;
    }

    public void setHarm(Boolean harm) {
        this.harm = harm;
    }

    public List<String> getIndividualsInvolved() {
        return individualsInvolved;
    }

    public void setIndividualsInvolved(List<String> individualsInvolved) {
        this.individualsInvolved = individualsInvolved;
    }

    public List<String> getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(List<String> eventCategory) {
        this.eventCategory = eventCategory;
    }

    public boolean isIncidentEffect() {
        return incidentEffect;
    }

    public void setIncidentEffect(boolean incidentEffect) {
        this.incidentEffect = incidentEffect;
    }

    public List<String> getWitnessName() {
        return witnessName;
    }

    public void setWitnessName(List<String> witnessName) {
        this.witnessName = witnessName;
    }


    public List<String> getDepartmentsInvolved() {
        return departmentsInvolved;
    }

    public void setDepartmentsInvolved(List<String> departmentsInvolved) {
        this.departmentsInvolved = departmentsInvolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSSN() {
        return patientSSN;
    }

    public void setPatientSSN(String patientSSN) {
        this.patientSSN = patientSSN;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public List<String> getWitnessTelephone() {
        return witnessTelephone;
    }

    public void setWitnessTelephone(List<String> witnessTelephone) {
        this.witnessTelephone = witnessTelephone;
    }
}
