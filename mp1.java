import java.io.*;
import java.io.IOException;
import java.util.*;


public class mp1 {
    String askUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    void readFile(File f) throws  Exception{
        String line = null;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            varDeclaration(br);

            br.close();
        }catch (FileNotFoundException e){
            System.out.print("File does not exist!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    String arr[] = {"int","float","char","double"};
    void varDeclaration(BufferedReader br) throws Exception{
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
    void displayArr(ArrayList<String> arr){
        for(int i = 0; i < arr.size();i++){
            System.out.print(arr.get(i));
            System.out.print("\n");
        }
    }

    public static void main(String args[])  throws  Exception {
        mp1 mp = new mp1();
        mp.readFile(new File("C://Users//thegi//Desktop//test.txt"));

    }
}
