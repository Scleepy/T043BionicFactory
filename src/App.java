// JAVA PROJECT 1 
// Nama: Daniel Yohanes 
// NIM: 2501975261
// Trainee Number:T043

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        int workers = 1;
        ArrayList<String> bionicName = new ArrayList<String>();
        ArrayList<String> bionicType = new ArrayList<String>();
        ArrayList<String> durabiltyClass = new ArrayList<String>();
        ArrayList<String> dateCreated = new ArrayList<String>();
        ArrayList<Integer> daySinceCreated = new ArrayList<Integer>();
        ArrayList<String> status = new ArrayList<String>();
        ArrayList<Integer> buildTime = new ArrayList<Integer>();
        ArrayList<Integer> testDuration = new ArrayList<Integer>();

        LocalDate date = LocalDate.now();

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
    }

    public static void showMenu(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        char sym = '|';

        System.out.println("==================================================================================================================");
        System.out.println("|No| Bionic Name     | Bionic Type | Durability Class | Date Created         | Day Since Created | Status        |");
        System.out.println("==================================================================================================================");
        if(bionicName.size() == 0){
            System.out.println("|                                                    Empty                                                       |");
        } else {
            for(int i=0; i < bionicName.size();i++){

                LocalDate printDate = LocalDate.parse(dateCreated.get(i));
                String printDateFormat = printDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

                System.out.printf("%c%-2d|", sym, i + 1);
                System.out.printf(" %-16s|", bionicName.get(i));
                System.out.printf(" %-12s|", bionicType.get(i));
                System.out.printf(" %-17s|", durabiltyClass.get(i));
                System.out.printf(" %-21s|", printDateFormat);
                System.out.printf(" %-10s day(s) |", daySinceCreated.get(i));
                System.out.printf(" %-14s|", status.get(i));
                System.out.println();
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        System.out.println("\n");

        String today = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        System.out.println("Current date: " + today); 
        System.out.println("\n");

        System.out.println("1. Build Bionic");
        System.out.println("2. Manage Bionic");
        System.out.println("3. Manage Worker");
        System.out.println("4. Sort by Type");
        System.out.println("5. Sort by Durability");
        System.out.println("6. Sort by Date Created");
        System.out.println("7. Next Day");
        System.out.println("8. Exit");
        System.out.print(">> ");

        int choice = 0;

        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch(Exception e){
            System.out.println("Input must be an integer");
            showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        } 


        switch(choice){
            case 1:
            buildBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 2:
            manageBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 3:
            manageWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 4:
            sortByType(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 5:
            sortByDurability(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 6:
            sortByDateCreated(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 7:
            nextDay(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 8:
            System.exit(0);
                break;
            default:
            showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            }
    
            scanner.close();

    }

    public static void nextDay(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        date = date.plusDays(1);
        int value = 0;

        for(int i = 0; i < bionicName.size(); i++){
            value = daySinceCreated.get(i) + 1;
        
            daySinceCreated.set(i, value);
        }

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

    }

    public static void buildBionic(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){{
        
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateCreate = date.format(formatter);

        dateCreated.add(dateCreate);

        String name;
        System.out.print("Bionic Name [min 5 characters & unique]: ");
        name = scanner.nextLine();


        while(name.length() < 5 || bionicName.contains(name)){
            System.out.print("Bionic Name [min 5 characters & unique]: ");
            name = scanner.nextLine();
        }

        bionicName.add(name);

        String type;

        String eye = "Eye";
        String arm = "Arm";
        String leg = "Leg";

        do{

            System.out.print("Bionic Type [Eye, Arm, Leg]: ");
            type = scanner.nextLine();

        }while(!type.equals(eye) && !type.equals(arm) && !type.equals(leg));

        
        bionicType.add(type);

        String durability;

        do{

            System.out.print("Bionic Durability Class [A,B,C,D,E]: ");
            durability = scanner.nextLine();

        }while(!durability.equals("A") && !durability.equals("B") && !durability.equals("C") && !durability.equals("D") && !durability.equals("E"));

        durabiltyClass.add(durability);


        int duration = 0; 
        int flag = 0;

        do{

            try {
                Scanner innerScan = new Scanner(System.in);
                System.out.print("Total months estimated until bionic is done [min 5] (ex. 8): ");
                duration = innerScan.nextInt();

                flag = 0;

            } catch (Exception e) {
                flag = 1; 
            }

        }while(flag == 1 || duration < 5); 
        
        
        System.out.println("\n");
        System.out.println("Build started for bionic: " + name);
        System.out.println("Bionic type             : " + type);
        System.out.println("Bionic durability class : " + durability);
        System.out.println("Estimated done in       : " + duration + " months");
        System.out.println("\n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      
        //Chance processing

        Random rand = new Random();

        int firstChance = rand.nextInt(2); // 0 or 1

        int secondChance = rand.nextInt(2) + 1; // 1 till 2 months

        if(firstChance == 0){
            //Increased work time
            duration += secondChance;
        } else {
            //Decreased work time
            duration -= secondChance;
        }

        int totalMonths = duration / workers; 

        //System.out.println("total months: " + totalMonths);

        float percentage = (float) 100 / (totalMonths);
        float totalPercentage = 0;

        //System.out.println(percentage);
        //System.out.println(totalPercentage);
        
        for(int i = 0; i <= totalMonths; i++){
            System.out.printf("Building... %d month(s) [%.2f%%]\n", i, totalPercentage);
            totalPercentage += percentage;

            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //COUNTING DIFFERENCE

        LocalDate future = date.plusMonths(totalMonths);

        long dayDiff = Math.abs(ChronoUnit.DAYS.between(date, future));

        System.out.println();

        int days = 0;

        for(int i = 0; i < bionicName.size() - 1; i++){
            days = daySinceCreated.get(i) + (int) dayDiff; 

            daySinceCreated.set(i, days);
        }

        String buildDate = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));

        System.out.println("Start Date: " + buildDate);

        date = date.plusMonths(totalMonths);

        buildDate = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        
        System.out.println("Start Date: " + buildDate);

        System.out.println("Bionic done in " + totalMonths + " months [press enter]");

        scanner.nextLine();


        daySinceCreated.add(0);
        status.add("Need for test");
        buildTime.add(totalMonths);
        testDuration.add(0);

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

        scanner.close();

        }
    }

    public static void manageWorker(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Current Worker: " + workers + " worker(s)");
        System.out.println("1. Hire new worker");
        System.out.println("2. Fire worker");
        System.out.println("3. Back");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt(); 
        } catch (Exception e) {
            manageWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        switch (choice){
        case 1:
        hireWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            break;
        case 2:
        fireWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            break;
        case 3:
        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            break;
        default:
        manageWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            break;

        }

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

        scanner.close();
    }

    public static void hireWorker(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Input number of worker you want to hire: ");

        int count = 0;

        try {
            count = scanner.nextInt(); 
        } catch (Exception e) {
            hireWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        if(count == 0){
            hireWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        workers += count;

        System.out.print("Worker Hired !! [press enter]");
        scanner.nextLine(); scanner.nextLine();

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

        scanner.close();
    }

    public static void fireWorker(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);

        if(workers == 1){
            System.out.println("You only have 1 worker, cannot fire worker. [press enter]");
            scanner.nextLine();
            showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        int count = 0;

        int maxWorkers = workers - 1;

        System.out.printf("Input number of worker you want to fire [max %d candidate(s)]: \n", maxWorkers);

        try {
            count = scanner.nextInt(); 
        } catch (Exception e) {
            fireWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        if(count == 0 || count == workers || count > workers){
            fireWorker(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        workers -= count;

        System.out.print("Worker Fired !! [press enter]");
        scanner.nextLine(); scanner.nextLine();

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

        scanner.close();
    }

    public static void manageBionic(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Update Bionic");
        System.out.println("2. Remove Bionic");
        System.out.println("3. Test Bionic");
        System.out.println("4. Show Test History");
        System.out.println("5. Back");
        System.out.print(">> ");

        int choice = 0;

        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            manageBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        }

        switch (choice) {
            case 1:
            updateBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 2:
            removeBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 3:
            testBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            case 4:
            testHistory(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);    
                break;
            case 5:
            showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
            default:
            manageBionic(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
                break;
        }
        scanner.close();
    }

    public static void updateBionic(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);

        char sym = '|';

        System.out.println("==================================================================================================================");
        System.out.println("|No| Bionic Name     | Bionic Type | Durability Class | Date Created         | Day Since Created | Status        |");
        System.out.println("==================================================================================================================");
        if(bionicName.size() == 0){
            System.out.println("|                                                    Empty                                                       |");
        } else {
            for(int i=0; i < bionicName.size();i++){
                System.out.printf("%c%-2d|", sym, i + 1);
                System.out.printf(" %-16s|", bionicName.get(i));
                System.out.printf(" %-12s|", bionicType.get(i));
                System.out.printf(" %-17s|", durabiltyClass.get(i));
                System.out.printf(" %-21s|", dateCreated.get(i));
                System.out.printf(" %-10s day(s) |", daySinceCreated.get(i));
                System.out.printf(" %-14s|", status.get(i));
                System.out.println();
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        String name;

        do{ 
            
            System.out.print("Input bionic name to update ['cancel' to go back]: ");

            name = scanner.nextLine();

            if(name.equals("cancel")){
                showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            }
            
        }while(!bionicName.contains(name));

        int index = 0;

        for(int i = 0; i < bionicName.size(); i++) {
            if(bionicName.get(i).equals(name)){
                index = i; 
            }
        }

        //System.out.println(index);

        String newName;

        System.out.print("Bionic Name [min 5 characters & unique]: ");
        newName = scanner.nextLine();

        while(newName.length() < 5 || bionicName.contains(newName)){
            System.out.print("Bionic Name [min 5 characters & unique]: ");
            newName = scanner.nextLine();
        }

        bionicName.set(index, newName);

        String newDurability;

        do{

            System.out.print("Bionic Durability Class [A,B,C,D,E]: ");
            newDurability = scanner.nextLine();

        }while(!newDurability.equals("A") && !newDurability.equals("B") && !newDurability.equals("C") && !newDurability.equals("D") && !newDurability.equals("E"));

        durabiltyClass.set(index, newDurability);

        System.out.println("Bionic Update !! [press enter]");
        scanner.nextLine();

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

        scanner.close();
    }

    public static void removeBionic(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);

        char sym = '|';

        System.out.println("==================================================================================================================");
        System.out.println("|No| Bionic Name     | Bionic Type | Durability Class | Date Created         | Day Since Created | Status        |");
        System.out.println("==================================================================================================================");
        if(bionicName.size() == 0){
            System.out.println("|                                                    Empty                                                       |");
        } else {
            for(int i=0; i < bionicName.size();i++){
                System.out.printf("%c%-2d|", sym, i + 1);
                System.out.printf(" %-16s|", bionicName.get(i));
                System.out.printf(" %-12s|", bionicType.get(i));
                System.out.printf(" %-17s|", durabiltyClass.get(i));
                System.out.printf(" %-21s|", dateCreated.get(i));
                System.out.printf(" %-10s day(s) |", daySinceCreated.get(i));
                System.out.printf(" %-14s|", status.get(i));
                System.out.println();
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        String name;

        do{ 
            
            System.out.print("Input bionic name to remove ['cancel' to go back]: ");

            name = scanner.nextLine();

            if(name.equals("cancel")){
                showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            }
            
        }while(!bionicName.contains(name));


        for(int i = 0; i < bionicName.size(); i++){
            if(bionicName.get(i).equals(name)){
                bionicName.remove(i);
                bionicType.remove(i);
                durabiltyClass.remove(i);
                dateCreated.remove(i);
                daySinceCreated.remove(i);
                status.remove(i);
            }
        }

        System.out.println(name + " removed !! [press enter]");

        scanner.nextLine(); 

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

        scanner.close();
    }

    public static void testBionic(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);

        char sym = '|';

        System.out.println("==================================================================================================================");
        System.out.println("|No| Bionic Name     | Bionic Type | Durability Class | Date Created         | Day Since Created | Status        |");
        System.out.println("==================================================================================================================");
        if(bionicName.size() == 0){
            System.out.println("|                                                    Empty                                                       |");
        } else {
            for(int i=0; i < bionicName.size();i++){
                System.out.printf("%c%-2d|", sym, i + 1);
                System.out.printf(" %-16s|", bionicName.get(i));
                System.out.printf(" %-12s|", bionicType.get(i));
                System.out.printf(" %-17s|", durabiltyClass.get(i));
                System.out.printf(" %-21s|", dateCreated.get(i));
                System.out.printf(" %-10s day(s) |", daySinceCreated.get(i));
                System.out.printf(" %-14s|", status.get(i));
                System.out.println();
            }
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        String name;
        int index; 
        int flag = 0;
        do{ 
            flag = 0;
            
            System.out.print("Input bionic name to test ['cancel' to go back]: ");

            name = scanner.nextLine();

            if(name.equals("cancel")){
                showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
            }

            for(int i = 0; i < bionicName.size(); i++) {
                if(bionicName.get(i).equals(name)){
                    index = i; 
                    if(status.get(i).equals("PASSED") || status.get(i).equals("FAILED")){
                        flag = 1;
                    }
                }
            }

        
 
        }while(!bionicName.contains(name) || flag == 1);


        index = 0;

        for(int i = 0; i < bionicName.size(); i++) {
            if(bionicName.get(i).equals(name)){
                index = i; 
            }
        }

        //CHANCE GENERATOR
        Random rand = new Random();

        int months = buildTime.get(index);

        int chance = 0;

        if(months >= 20){
            chance = rand.nextInt(31) + 70;
        } else if (months >= 12){
            chance = rand.nextInt(41) + 30;
        } else {
            chance = rand.nextInt(31) + 0;
        }

        System.out.println();

        System.out.printf("Running bionic test on bionic '%s'\n", name);
        System.out.println("Bionic build time: " + buildTime.get(index) + " months old");
        System.out.printf("Test started with %d%% success rate. [press enter to continue]\n", chance);
        scanner.nextLine();

        int duration = 0;
        duration = rand.nextInt(7001) + 7000;

        int repetition = duration / 1000;

        float percentage = (float) 100 / repetition;
        float totalPercentage = 0;

        //WIN CHECKING
        int pass = 0;

        pass = rand.nextInt(101);

        int number = 0;
        if(chance < pass){
            number = 1; 
        }

        for(int i = number; i <= repetition; i++){
            System.out.printf("Testing bionic... [%.2f%%]\n", totalPercentage);
            totalPercentage += percentage; 

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        totalPercentage -= percentage;

        System.out.println("Test Finished\n");
        System.out.println();

        String stat;

        if(totalPercentage < 99){
            System.out.println("Bionic test result: FAILED");
            System.out.println("Bionic will not last for long time... [press enter]");
            stat = "FAILED"; 
        } else {
            repetition++;
            System.out.println("Bionic test result: PASSED");
            System.out.println("Bionic is ready to use... [press enter]");
            stat = "PASSED"; 
        }
        scanner.nextLine();

        status.set(index, stat);

        testDuration.set(index, repetition);

        //MAKE NEW ARRAYLIST FOR TIME COMPLETED -> repetition in hours, pass it into showMenu

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);
        scanner.close();
    }

    public static void testHistory(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        Scanner scanner = new Scanner(System.in);

        char sym = '|';

        int flag = 0;

        if(!status.contains("PASSED") && !status.contains("FAILED")){
            flag = 1;
        }

        //System.out.println("flag is: " + flag);

        System.out.println("=====================================================================================");
        System.out.println("|No| Bionic Name     | Bionic Type | Build Time      | Test Duration   | Status     |");
        System.out.println("=====================================================================================");
        if(bionicName.size() == 0 || flag == 1){
            System.out.println("|                                     Empty                                         |");
        }else {

            int counter = 1;

            //PASSED FIRST
            for(int i = 0; i < bionicName.size(); i++){

                if(status.get(i).equals("PASSED")){
                System.out.printf("%c%-2d|", sym, counter);
                System.out.printf(" %-16s|", bionicName.get(i));
                System.out.printf(" %-12s|", bionicType.get(i));
                System.out.printf(" %-8s months |", buildTime.get(i));
                System.out.printf(" %-9s hours |", testDuration.get(i));
                System.out.printf(" %-11s|", status.get(i));
                System.out.println();
                counter++;
                }
            }

            //FAILED AFTER
            for(int i = 0; i < bionicName.size(); i++){
            
                if(status.get(i).equals("FAILED")){
                    System.out.printf("%c%-2d|", sym, counter);
                    System.out.printf(" %-16s|", bionicName.get(i));
                    System.out.printf(" %-12s|", bionicType.get(i));
                    System.out.printf(" %-8s months |", buildTime.get(i));
                    System.out.printf(" %-9s hours |", testDuration.get(i));
                    System.out.printf(" %-11s|", status.get(i));
                    System.out.println();
                    counter++;
                }
            }

        }
        System.out.println("=====================================================================================");
        System.out.println();
        System.out.println("[press enter]");

        scanner.nextLine();
        

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);  

        scanner.close();
    }

    public static void sortByType(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        
        String temp, temp2, temp3, temp4, temp6;
        int temp5;

        // SORTING BY BIONIC TYPE
        for (int i = 0; i < bionicName.size(); i++) {
            for (int j = 0; j < bionicName.size(); j++) {

                if (bionicType.get(i).compareTo(bionicType.get(j)) < 0) {
                    // Swapping Names
                    temp = bionicName.get(i);
                    bionicName.set(i, bionicName.get(j));
                    bionicName.set(j, temp);

                    // Swapping Types
                    temp2 = bionicType.get(i);
                    bionicType.set(i, bionicType.get(j));
                    bionicType.set(j, temp2);

                    // Swapping Durability
                    temp3 = durabiltyClass.get(i);
                    durabiltyClass.set(i, durabiltyClass.get(j));
                    durabiltyClass.set(j, temp3);

                    // Swapping Days Created
                    temp4 = dateCreated.get(i);
                    dateCreated.set(i, dateCreated.get(j));
                    dateCreated.set(j, temp4);

                    // Swapping Day Since Created
                    temp5 = daySinceCreated.get(i);
                    daySinceCreated.set(i, daySinceCreated.get(j));
                    daySinceCreated.set(j, temp5);

                    // Swapping Status
                    temp6 = status.get(i);
                    status.set(i, status.get(j));
                    status.set(j, temp6);
                }

            }
        }

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

    }

    public static void sortByDurability(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        
        String temp, temp2, temp3, temp4, temp6;
        int temp5;

        for (int i = 0; i < bionicName.size(); i++) {
            for (int j = 0; j < bionicName.size(); j++) {

                if (durabiltyClass.get(i).compareTo(durabiltyClass.get(j)) < 0) {
                    // Swapping Names
                    temp = bionicName.get(i);
                    bionicName.set(i, bionicName.get(j));
                    bionicName.set(j, temp);

                    // Swapping Types
                    temp2 = bionicType.get(i);
                    bionicType.set(i, bionicType.get(j));
                    bionicType.set(j, temp2);

                    // Swapping Durability
                    temp3 = durabiltyClass.get(i);
                    durabiltyClass.set(i, durabiltyClass.get(j));
                    durabiltyClass.set(j, temp3);

                    // Swapping Days Created
                    temp4 = dateCreated.get(i);
                    dateCreated.set(i, dateCreated.get(j));
                    dateCreated.set(j, temp4);

                    // Swapping Day Since Created
                    temp5 = daySinceCreated.get(i);
                    daySinceCreated.set(i, daySinceCreated.get(j));
                    daySinceCreated.set(j, temp5);

                    // Swapping Status
                    temp6 = status.get(i);
                    status.set(i, status.get(j));
                    status.set(j, temp6);
                }

            }
        }

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

    }

    public static void sortByDateCreated(ArrayList<String> bionicName, ArrayList<String> bionicType, ArrayList<String> durabiltyClass, ArrayList<String> dateCreated, ArrayList<Integer> daySinceCreated, ArrayList<String> status, LocalDate date, int workers, ArrayList<Integer> buildTime, ArrayList<Integer> testDuration){
        
        String temp, temp2, temp3, temp4, temp6;
        int temp5;

        for (int i = 0; i < bionicName.size(); i++) {
            for (int j = 0; j < bionicName.size(); j++) {
                LocalDate firstDate = LocalDate.parse(dateCreated.get(i));
                LocalDate secondDate = LocalDate.parse(dateCreated.get(j));
                if (firstDate.compareTo(secondDate) < 0) {

                    // Swapping Names
                    temp = bionicName.get(i);
                    bionicName.set(i, bionicName.get(j));
                    bionicName.set(j, temp);

                    // Swapping Types
                    temp2 = bionicType.get(i);
                    bionicType.set(i, bionicType.get(j));
                    bionicType.set(j, temp2);

                    // Swapping Durability
                    temp3 = durabiltyClass.get(i);
                    durabiltyClass.set(i, durabiltyClass.get(j));
                    durabiltyClass.set(j, temp3);

                    // Swapping Days Created
                    temp4 = dateCreated.get(i);
                    dateCreated.set(i, dateCreated.get(j));
                    dateCreated.set(j, temp4);

                    // Swapping Day Since Created
                    temp5 = daySinceCreated.get(i);
                    daySinceCreated.set(i, daySinceCreated.get(j));
                    daySinceCreated.set(j, temp5);

                    // Swapping Status
                    temp6 = status.get(i);
                    status.set(i, status.get(j));
                    status.set(j, temp6);
                }
            }
        }

        showMenu(bionicName, bionicType, durabiltyClass, dateCreated, daySinceCreated, status, date, workers, buildTime, testDuration);

    }


}
