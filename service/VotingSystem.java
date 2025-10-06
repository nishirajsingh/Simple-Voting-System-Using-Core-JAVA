package service;

import model.Candidate;
import model.Voter;
import java.time.LocalDateTime;

public class VotingSystem {
    private Candidate[] candidates;
    private Voter[] voters;
    private int candidateCount;
    private int voterCount;
    private int voterIdCounter = 101;

    public VotingSystem(int maxCandidates, int maxVoters) {
        candidates = new Candidate[maxCandidates];
        voters = new Voter[maxVoters];
        candidateCount = 0;
        voterCount = 0;
    }

    public void addCandidate(String name, String party) {
        if (candidateCount < candidates.length) {
            candidates[candidateCount++] = new Candidate(name, party);
        }
    }

    // Check if Aadhaar already exists
    private boolean isAadhaarRegistered(String aadhaar) {
        for (int i = 0; i < voterCount; i++) {
            if (voters[i].getAadhaar().equals(aadhaar)) {
                return true;
            }
        }
        return false;
    }

    public void registerVoter(String name, String aadhaar, String address) {
        if (isAadhaarRegistered(aadhaar)) {
            System.out.println("❌ Aadhaar No. " + aadhaar + " is already registered!");
            return;
        }

        if (voterCount < voters.length) {
            String voterId = "NGF" + voterIdCounter++;
            Voter v = new Voter(voterId, name, aadhaar, address);
            voters[voterCount++] = v;
            v.showDetails();
        }
    }

    private Voter findVoter(String voterId) {
        for (int i = 0; i < voterCount; i++) {
            if (voters[i].getVoterId().equals(voterId)) {
                return voters[i];
            }
        }
        return null;
    }

    public void castVote(String voterId, int candidateIndex) {
        Voter voter = findVoter(voterId);

        if (voter == null) {
            System.out.println("❌ Invalid Voter ID! Please register first.");
            return;
        }

        if (voter.hasVoted()) {
            System.out.println("⚠️ You have already voted! Duplicate votes are not allowed.");
            return;
        }

        if (candidateIndex < 0 || candidateIndex >= candidateCount) {
            System.out.println("❌ Invalid candidate choice!");
            return;
        }

        Candidate candidate = candidates[candidateIndex];
        candidate.addVote();
        voter.setVoted();

        // Print Voting Receipt
        System.out.println("\n==================== VOTING RECEIPT ====================");
        System.out.println("Voter Name   : " + voter.getName());
        System.out.println("Voter ID     : " + voter.getVoterId());
        System.out.println("Aadhaar No.  : " + voter.getAadhaar());
        System.out.println("Address      : " + voter.getAddress());
        System.out.println("--------------------------------------------------------");
        System.out.println("Voted For    : " + candidate.getName() + " (" + candidate.getParty() + ")");
        System.out.println("Date & Time  : " + LocalDateTime.now());
        System.out.println("========================================================\n");
    }

    public void showCandidates() {
        System.out.println("\nList of Candidates:");
        for (int i = 0; i < candidateCount; i++) {
            System.out.println(i + ". " + candidates[i].getName() + " (" + candidates[i].getParty() + ")");
        }
    }

    public void showResults() {
        System.out.println("\n📊 Voting Results:");
        int maxVotes = 0;

        for (int i = 0; i < candidateCount; i++) {
            System.out.println(candidates[i].getName() + " (" + candidates[i].getParty() + ") - " +
                    candidates[i].getVotes() + " votes");
            if (candidates[i].getVotes() > maxVotes) {
                maxVotes = candidates[i].getVotes();
            }
        }

        System.out.println("\n🏆 Winner(s):");
        for (int i = 0; i < candidateCount; i++) {
            if (candidates[i].getVotes() == maxVotes) {
                System.out.println(candidates[i].getName() + " (" + candidates[i].getParty() + ")");
            }
        }
    }
}
