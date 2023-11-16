package org.openjfx;
import java.util.*;


public class WorkFlow {
    // Queues 
    private static Queue<String> reviewQ = new LinkedList<>();
    private static Queue<String> approvalQ = new LinkedList<>();

    protected static int addWorkFlow(String refNumber, String target){    
        
        if ((refNumber == null) || (target == null)){
            //System.out.println("Passing NULL values");      // For DB
            return -1;
        }
       

        if((target.equals("Reviewer")) && (Integer.parseInt(refNumber) > 0)){
            reviewQ.add(refNumber);
            //System.out.println(reviewQ);        // For DB
        }
        else if((target.equals("Approver")) && (Integer.parseInt(refNumber) > 0)){
            approvalQ.add(refNumber);
            //System.out.println(approvalQ);      // For DB

        }
        else{
            System.out.println("Error! Target invalid");
            return -1;          
        }
        return 1;           
    }

    protected static String getNextRef(String employee){
        if((employee.equals("Reviewer")) && (!reviewQ.isEmpty())){
            return reviewQ.remove();
        }
        else if ((employee.equals("Approver")) && (!approvalQ.isEmpty())) {
            return approvalQ.remove();
        }
        else{
            return "No item to continue, take a break!";          
        }
    }

    // Uncomment to DB
/*  
    public static void main(String[] args){
        WorkFlow.addWorkFlow("-1", "Reviewer");
        WorkFlow.addWorkFlow("123", "Janitorial");
        WorkFlow.addWorkFlow(null, "Reviewer");
        WorkFlow.addWorkFlow("123", null);
        WorkFlow.addWorkFlow(null, null);
        

        WorkFlow.addWorkFlow("111", "Reviewer");
        WorkFlow.addWorkFlow("222", "Approver");
        WorkFlow.addWorkFlow("333", "Reviewer");

        System.out.println("Get reviewer : " + WorkFlow.getNextRef("Reviewer"));
        System.out.println("Get reviewer : " + WorkFlow.getNextRef("Reviewer"));
        System.out.println("Get reviewer empty: " + WorkFlow.getNextRef("Reviewer"));

        System.out.println("Get approval : " + WorkFlow.getNextRef("Approver"));
        System.out.println("Get approval empty: " + WorkFlow.getNextRef("Approver"));   
        


    }

*/





}
