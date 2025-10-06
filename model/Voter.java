package model;

public class Voter {
    private String voterId;
    private String name;
    private String aadhaar;
    private String address;
    private boolean hasVoted;

    public Voter(String voterId, String name, String aadhaar, String address) {
        this.voterId = voterId;
        this.name = name;
        this.aadhaar = aadhaar;
        this.address = address;
        this.hasVoted = false;
    }

    public String getVoterId() {
        return voterId;
    }

    public String getName() {
        return name;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public String getAddress() {
        return address;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setVoted() {
        hasVoted = true;
    }

    public void showDetails() {
        System.out.println("✅ Voter Registered Successfully!");
        System.out.println("Name: " + name);
        System.out.println("Aadhaar: " + aadhaar);
        System.out.println("Address: " + address);
        System.out.println("Voter ID: " + voterId);
    }
}
