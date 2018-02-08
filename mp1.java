import java.io.*;
import java.util.*;


public class mp1 {
    static String askUserInput() {
        System.out.print("Enter Input File:");
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    static void readFile(String fName) {
        String line = null;
        try (FileReader fr = new FileReader(fName);
            BufferedReader br = new BufferedReader(fr);){
            int n = Integer.parseInt( br.readLine());
            System.out.println("This is the number of testcases:" + n);
            String testcase = "";
            boolean isFunc = false;
            while((line = br.readLine())!= null){
                if (line.contains(";") && !isFunc){
                    int index = 0, prev = 0;
                    while((index = line.indexOf(";", prev)) >= 0){
                        testcase = testcase.concat(line.substring(prev, index).trim());
                        System.out.println(testcase);
                        // call validate();
                        testcase = "";
                        prev = index + 1;
                    }  
                    System.out.println(index + "    " + prev);
                          
                } else if(line.contains("{")){
                    testcase = testcase.concat(line);
                    isFunc = true;
                } 
                else{
                    testcase = testcase.concat(line);
                } 
            }
            br.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    public static void main(String args[]) {
//        String fName = askUserInput();
        String fName = "others/mpa1.in";
        readFile(fName);
        
        
    }
}
