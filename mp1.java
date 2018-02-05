import java.io.*;
import java.io.IOException;
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
            varDeclaration(br);
            br.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    String arr[] = {"int","float","char","double"};
    static void varDeclaration(BufferedReader br) throws Exception{
        String line = br.readLine();
        String newStr = "";

        if(Integer.parseInt(line) <= 0){
            System.out.print("No testcases!");
            System.exit(0);
        }
        ArrayList<String> strArr = new ArrayList<>();
        while(line != null) {
            line = line.trim();
            if(!(line.length() == 0)){

                strArr.add(line + ',');
                line = br.readLine();
            } else {
                line = br.readLine();
            }
        }
        displayArr(strArr);
    }
    static void displayArr(ArrayList<String> arr){
        for(int i = 0; i < arr.size();i++){
            System.out.print(arr.get(i));
            System.out.print("\n");
        }
    }

    public static void main(String args[]) {
        String fName = askUserInput();
        readFile(fName);
        System.out.println("wowowowo");
    }
}
