// For this project you are going to practice using inheritance, interfaces and abstract classes to
// relate objects to one another. The following is a description of each class and its behavior. It is
// up to you to decide which classes should extend, implement or abstract which pieces to
// maximize your code sharing.


public class CompanyStructure {
    public static void main(String[] args) {
        TechnicalLead CTO = new TechnicalLead("Satya Nadella");
        SoftwareEngineer seA = new SoftwareEngineer("Kasey");
        SoftwareEngineer seB = new SoftwareEngineer("Breana");
        SoftwareEngineer seC = new SoftwareEngineer("Eric");
        CTO.addReport(seA);
        CTO.addReport(seB);
        CTO.addReport(seC);
        System.out.println(CTO.getTeamStatus());

        TechnicalLead VPofENG = new TechnicalLead("Bill Gates");
        SoftwareEngineer seD = new SoftwareEngineer("Winter");
        SoftwareEngineer seE = new SoftwareEngineer("Libby");
        SoftwareEngineer seF = new SoftwareEngineer("Gizan");
        SoftwareEngineer seG = new SoftwareEngineer("Zaynah");
        VPofENG.addReport(seD);
        VPofENG.addReport(seE);
        VPofENG.addReport(seF);
        VPofENG.addReport(seG);
        System.out.println(VPofENG.getTeamStatus());

        BusinessLead CFO = new BusinessLead("Amy Hood");
        Accountant actA = new Accountant("Niky");
        Accountant actB = new Accountant("Andrew");
        CFO.addReport(actA, CTO);
        CFO.addReport(actB, VPofENG);
        System.out.println(CFO.getTeamStatus());
    }
}

class Employee {
    String name;
    double baseSalary;
    double salary;
    int ID;
    static int id = 0;
    Employee manager;

    public Employee(String name, double baseSalary){
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary(){
        return salary;
    }

    public String getName(){
        return name;
    }

    public int getEmployeeID(){
        id++;
        ID = id;
        return ID;
    }

    public Employee getManager(){
        return manager;
    }

    public boolean equals(Employee other) {
        return (this.ID == other.ID);
    }

    public String toString(){
        return ID + " " + name;
    }

    public String employeeStatus(){
        return "";
    }
}

class TechnicalEmployee extends Employee{
    int check;

    public TechnicalEmployee(String name){
        super(name, 75000);
    }

    public String employeeStatus(){
        return toString() + " has " + check + " successful check ins";
    }
}

class BusinessEmployee extends Employee{
    double budget;

    public BusinessEmployee(String name){
        super(name, 50000);
    }

    public double getBonusBudget(){
        return budget;
    }

    public String employeeStatus(){
        return toString() + " with a budget of " + budget;
    }
}

class SoftwareEngineer extends TechnicalEmployee{
    boolean access;
    TechnicalLead manager;

    public SoftwareEngineer(String name){
        super(name);
        access = false;
        check = 0;
    }

    public boolean getCodeAccess(){
        return access;
    }

    public void setCodeAccess(boolean access){
        this.access = access;
    }

    public int getSuccessfulCheckIns(){
        return check;
    }

    public boolean checkInCode(){
        if (manager.approveCheckIn(this)) {
            check++;
            return true;
        }else{
            access = false;
            return false;
        }
    }
}

class Accountant extends BusinessEmployee{
    TechnicalLead lead;

    public Accountant(String name){
        super(name);
        budget = 0;
        lead = null;
    }

    public TechnicalLead getTeamSupported(){
        return lead;
    }

    public void supportTeam(TechnicalLead lead){
        this.lead = lead;
        for (int k = 0; k < lead.number; k++){
            budget += lead.directReports[k].baseSalary;
        }
        budget *= 1.1;
    }

    public boolean approveBonus(double bonus){
        return (budget >= bonus && lead != null);
    }

    public String employeeStatus(){
        return toString() + " with a bidget of " + budget + " is supporting " + lead.name;
    }
}

class TechnicalLead extends TechnicalEmployee{
    int headcount;
    int number = 0;
    SoftwareEngineer directReports[];
    BusinessLead lead;

    public TechnicalLead(String name){
        super(name);
        baseSalary = 1.3 * 75000;
        headcount = 4;
    }

    public boolean hasHeadCount(){
        return (number < headcount);
    }

    public boolean addReport(SoftwareEngineer e){
        if (hasHeadCount()){
            directReports[number] = e;
            number++;
            return true;
        }else {
            return false;
        }
    }

    public boolean approveCheckIn(SoftwareEngineer e){
        for (int i = 0; i < number; i++){
            if (directReports[i] == e){
                if (e.access){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean requestBonus(Employee e, double bonus){
        if (lead.approveBonus(e, bonus)){
            e.salary += bonus;
            return true;
        }else{
            return false;
        }
    }

    public String getTeamStatus(){
        if (number == 0){
            return toString() + " has " + check + " successful check ins and no direct reports yet";
        }else{
            String teamStatus = toString() + " has " + check + " successful check ins and is managing:";
            for (int j = 0; j < number; j++){
                teamStatus += directReports[j].toString() + " has " + directReports[j].check + " successful check ins";
            }
            return teamStatus;
        }
    }
}

class BusinessLead extends BusinessEmployee{
    int headCount;
    Accountant directReports[];
    int number = 0;

    public BusinessLead(String name){
        super(name);
        baseSalary = 2 * 50000;
        headCount = 10;
    }

    public boolean hasHeadCount(){
        return (number < headCount);
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam){
        if (hasHeadCount()){
            directReports[number] = e;
            number++;
            budget += 1.1 * e.baseSalary;
            e.lead = supportTeam;
            return true;
        }else{
            return false;
        }
    }

    public boolean requestBonus(Employee e, double bonus){
        if (bonus <= this.budget){
            e.salary += bonus;
            this.budget -= bonus;
            return true;
        }else{
            return false;
        }
    }

    public boolean approveBonus(Employee e, double bonus){
        for (int m = 0; m < number; m++){
            for (int n = 0; n < directReports[m].lead.number; n++){
                if (directReports[m].lead.directReports[n] == e){
                    if (directReports[m].budget >= bonus){
                        e.salary += bonus;
                    }
                }
            }
        }
        return false;
    }

    public String getTeamStatus(){
        if (number == 0){
            return toString() + " has no direct reports yet";
        }else{
            String teamStatus = toString() + " is managing:";
            for (int j = 0; j < number; j++){
                teamStatus += directReports[j].toString() + " is supporting " + directReports[j].lead;
            }
            return teamStatus;
        }
    }
}
