package com.cypherlab.cypher_lab.dto;

public class ModuleProgressDTO {
    private Long moduleId;
    private String moduleTitle;
    private Integer totalChallenges;
    private Integer solvedChallenges;
    private Integer totalPoints;
    private Integer earnedPoints;
    private String status;
    private Double completionPercentage;

    public ModuleProgressDTO() {}

    public ModuleProgressDTO(Long moduleId, String moduleTitle, Integer totalChallenges, 
                            Integer solvedChallenges, Integer totalPoints, Integer earnedPoints) {
        this.moduleId = moduleId;
        this.moduleTitle = moduleTitle;
        this.totalChallenges = totalChallenges;
        this.solvedChallenges = solvedChallenges;
        this.totalPoints = totalPoints;
        this.earnedPoints = earnedPoints;
        this.status = solvedChallenges.equals(totalChallenges) ? "completed" : solvedChallenges > 0 ? "inProgress" : "notStarted";
        this.completionPercentage = totalChallenges > 0 
            ? Math.round((solvedChallenges * 100.0) / totalChallenges * 100) / 100.0 
            : 0.0;
    }

    // Getters e Setters
    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public Integer getTotalChallenges() {
        return totalChallenges;
    }

    public void setTotalChallenges(Integer totalChallenges) {
        this.totalChallenges = totalChallenges;
    }

    public Integer getSolvedChallenges() {
        return solvedChallenges;
    }

    public void setSolvedChallenges(Integer solvedChallenges) {
        this.solvedChallenges = solvedChallenges;
        // Recalcula porcentagem quando atualiza
        if (this.totalChallenges != null && this.totalChallenges > 0) {
            this.completionPercentage = (solvedChallenges * 100.0) / this.totalChallenges;
        }
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Integer getEarnedPoints() {
        return earnedPoints;
    }

    public void setEarnedPoints(Integer earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(Double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
}