import java.io.*;
import java.util.*;

public class mp1 {
    static List<String> dataTypes = new ArrayList<>(Arrays.asList("int", "float", "double", "char"));

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
            ArrayList<String> lines = new ArrayList<>();
            while((line = br.readLine())!= null){
                lines.add(line);
            }
            getTestcase(lines);
            br.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    static void getTestcase(ArrayList<String> list){
        String testcase = "";
        boolean isFunc = false;
        for(String line: list){
            int start = 0;
            for (int i = 0; i < line.length(); i++){
                char c = line.charAt(i);
                if(c == ';' && !isFunc){
                    testcase += line.substring(start, i);
                    start = i + 1;
                    determineType(testcase.trim());
//                    validate(testcase.trim());
                    testcase = "";
                } else if(c == '{'){
                    isFunc = true;
                    testcase += line.substring(start, i + 1);
                    start = i + 1;
                } else if (c == '}'){
                    isFunc = false;
                    testcase += line.substring(start, i + 1);
                    determineType(testcase.trim());
//                    validate(testcase.trim());
                    start = i + 1;
                    testcase = "";
                }
            }
            testcase += line.substring(start) + " ";
        }
    }

    static void determineType(String testcase){
        String result = "";
        if (testcase.contains("{") && testcase.contains("=")){
            if (testcase.indexOf("{") < testcase.indexOf("=")){
                System.out.println("function definition");
            } else {
                System.out.println("variable definition");
            }
        } else if (testcase.contains("{")){
            System.out.println("function definition");
        } else if (testcase.contains("=")){
            System.out.println("variable definition");
        } else if (testcase.contains("(")){
            System.out.println("function declaration");
        } else {
            result = "VARIABLE DECLARATION";
            if (validVarDeclaration(testcase)){
                result = "VALID " + result;
            } else {
                result = "INVALID " + result;
            }
            System.out.println(result);
        }
    }

    static void validate(String testcase){
        if(testcase.contains("{")){
            System.out.println("function definition");
        } else if(testcase.contains("=")){
//            String t = testcase.substring(0, testcase.indexOf("="));
            String dtype1 = testcase.substring(0,testcase.indexOf(" "));
            if (dataTypes.contains(dtype1)) {
                String varname =  testcase.substring(testcase.indexOf(" ") + 1).trim();
                if(varname.contains(",,")){
                    System.out.println("INVALID VARIABLE DEFINITION");
                    return;
                }
                StringTokenizer tokens = new StringTokenizer(varname, ",");
                String name = "";
                while(tokens.hasMoreTokens()){
                    name = tokens.nextToken().trim();
                    if(name.contains("=")){
                        String value = name.substring(name.indexOf('=')+ 1).trim();
                        if(isInteger(value) && dtype1.equals(dataTypes.get(0))){
                            System.out.println(value + " is a valid Integer value");
                        }
                        if (isFloat(value) && dtype1.equals(dataTypes.get(1))) {
                            System.out.println(value + " is a valid float value");
                        }
                        if (isDouble(value) && dtype1.equals(dataTypes.get(2))) {
                            System.out.println(value + " is a valid Double value");
                        }
                        if (isChar(value) && dtype1.equals(dataTypes.get(3))) {
                            System.out.println(value + " is a valid char value");
                        }
                        System.out.println(dtype1 + " is NOT VALID");
                    } else {
                        if ((!isNum(name.charAt(0)) || isLetter(name.charAt(0))) || name.charAt(0) == '_') {
                            if(name.length() != 1){
                                if(validVarName(name)){
                                    System.out.print(name + " VALID VARNAME DECLARATION \n");
                                } else {
                                    System.out.print(name + " INVALID VARNAME DECLARATION \n");
                                }
                            } else {
                                System.out.print(name + " VALID VARNAME DECLARATION cuz length is 1" + "\n");
                            }

                        }
                    }

                }
            } else {
                System.out.println("INVALID VARIABLE DEFINITION");
            }


        } else if(testcase.contains("(")){
            System.out.println("function declaration");
        } else {
            String dtype = testcase.substring(0, testcase.indexOf(" "));
            if(dataTypes.contains(dtype)){
                String t = testcase.substring(testcase.indexOf(" ") + 1).trim();
                if(t.contains(",,")){
                    System.out.println("aINVALID VARIABLE DECLARATION");
                    return;
                }
                StringTokenizer st = new StringTokenizer(t, ",");
                List<String> declaredVar = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    String s = st.nextToken().trim();
                    if(declaredVar.contains(s)){
                        System.out.println("bINVALID VARIABLE DECLARATION");
                        return;
                    }
                    declaredVar.add(s);
                    if(!validVarName(s)){
                        System.out.println("cINVALID VARIABLE DECLARATION");
                        return;
                    }
                }
                System.out.println("VALID VARIABLE DECLARATION");
            } else {
                System.out.println("dINVALID VARIABLE DECLARATION");
            }
        }
    }
    static boolean validVarDeclaration(String test){
        String dType = test.substring(0, test.indexOf(" "));
        if(dataTypes.contains(dType)){ // check if valid data type
            String t = test.substring(test.indexOf(" ")).trim();
            if(t.contains(",,")){
                return false;
            }
            StringTokenizer st = new StringTokenizer(t, ","); // gets all the variable name if given is int x, b,c
            List<String> declaredVar = new ArrayList<>();
            while (st.hasMoreTokens()) {
                String s = st.nextToken().trim();
                if(declaredVar.contains(s)){
                    return false;
                }
                declaredVar.add(s);
                if(!validVarName(s)){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    // checks if the variable name is valid
    static boolean validVarName(String var){
        // checks if the variable is an empty string
        if(var.isEmpty()){
            return false;
        }
        ArrayList<String> reserved = new ArrayList<>(Arrays.asList("auto", "break", "case", "char",  "const", "continue", "default", "do",
                "int", "long", "register", "return", "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while",
                "double", "else", "enum", "extern", "float", "for", "goto", "if"));

        //checks if the variable name used is a reserved keyword
        for (String r: reserved) {
            if(var.equals(r)){
                return false;
            }
        }

        //checks every character of the variable name if its valid: (letter or _) + (letter or number or _)
        if(isLetter(var.charAt(0)) || var.charAt(0) == '_'){
            for (int i = 1; i < var.length(); i++){
                char x = var.charAt(i);
                if(!isLetter(x) && !isNum(x) &&  x != '_'){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    static boolean isInteger(String n){
        try{
            int x = Integer.parseInt(n);
            return true;
        } catch(Exception e){
            return false;
        }

    }
    static boolean isFloat(String n){
        try{
            float x = Float.parseFloat(n);
            return true;
        } catch(Exception e){
            return false;
        }

    }
    static boolean isDouble(String n){
        try{
            double x = Double.parseDouble(n);
            return true;
        } catch(Exception e){
            return false;
        }

    }
    static boolean isChar(String n){
        return n.length() == 3 && n.toCharArray()[0] == '\'' && n.toCharArray()[2] == '\'';
    }

    static boolean isNum(char x){
        if(x >= '0' && x <= '9') {
            return true;
        } else {
            return false;
        }
    }
    static boolean isLetter(char x){
        if((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {
//        String fName = askUserInput();
        String fName = "others/mpa1.in";
        readFile(fName);
    }
}
