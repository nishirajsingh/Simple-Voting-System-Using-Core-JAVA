import java.util.Scanner;
import java.util.InputMismatchException;
import service.VotingSystem;

public class VotingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VotingSystem votingSystem = new VotingSystem(10, 50);

        // Add candidates
        votingSystem.addCandidate("Narendra Modi", "🪷 BJP");
        votingSystem.addCandidate("Rahul Gandhi", "✋ INC");
        votingSystem.addCandidate("Mamta Banarjee", "🌱 TMC");
        votingSystem.addCandidate("Arvind Kejriwal", "🧹 AAP");
        votingSystem.addCandidate("Akhilesh Yadav", "🚲 SP");
        votingSystem.addCandidate("Mayawati", "🐘 BSP");

        System.out.println("=== Welcome to the Indian Voting System ===");

        while (true) {
            try {
                System.out.println("\n1. Register Voter");
                System.out.println("2. Show Candidates");
                System.out.println("3. Give Vote");
                System.out.println("4. Show Results");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        String aadhaar;
                        while (true) {
                            System.out.print("Enter Aadhaar No. (12 digits): ");
                            aadhaar = sc.nextLine();
                            if (aadhaar.matches("\\d{12}")) { 
                                break;
                            } else {
                                System.out.println("❌ Invalid Aadhaar No! Must be exactly 12 digits.");
                            }
                        }

                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();
                        votingSystem.registerVoter(name, aadhaar, address);
                        break;

                    case 2:
                        votingSystem.showCandidates();
                        break;

                    case 3:
                        System.out.print("Enter Voter ID: ");
                        String voterId = sc.next();
                        votingSystem.showCandidates();
                        int index;
                        while (true) {
                            System.out.print("Choose candidate index: ");
                            try {
                                index = sc.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("❌ Invalid input! Please enter a number.");
                                sc.next();
                            }
                        }
                        votingSystem.castVote(voterId, index);
                        break;

                    case 4:
                        votingSystem.showResults();
                        break;

                    case 5:
                        System.out.println("🙏 Thank you for participating in Indian Democracy!");
                        System.out.println("\n === Made By Nishiraj, Gopal and Vishal === \n");
                        sc.close();
                        return;

                    default:
                        System.out.println("❌ Invalid choice. Enter number between 1-5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                sc.next();
            }
        }
    }
}
