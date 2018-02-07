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
            while((line = br.readLine())!= null){
                System.out.println(line.indexOf(";"));
                System.out.println(line);
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
        System.out.println("wowowowo");
    }
}
