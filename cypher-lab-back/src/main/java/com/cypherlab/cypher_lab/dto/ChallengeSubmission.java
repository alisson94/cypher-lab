package com.cypherlab.cypher_lab.dto;

public class ChallengeSubmission {
    private String ans;
    
    public ChallengeSubmission() {}
    
    public ChallengeSubmission(String ans) {
        this.ans = ans;
    }
    
    public String getAns() {
        return ans;
    }
    
    public void setAns(String ans) {
        this.ans = ans;
    }
}
