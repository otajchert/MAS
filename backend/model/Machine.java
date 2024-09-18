package com.zbyszkobud.model;

import javax.persistence.Entity;

@Entity
public class Machine extends Equipment {
    private boolean requiresCertification;
    private String certificationName;

    // Gettery i settery
    public boolean isRequiresCertification() {
        return requiresCertification;
    }

    public void setRequiresCertification(boolean requiresCertification) {
        this.requiresCertification = requiresCertification;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }
}