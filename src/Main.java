import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Scanner;
public class Main {
    public static Node getString(Document doc, String id, String k, String a)
    {
        Element String=doc.createElement("String");
        String.appendChild(getstringElements(doc,String,"string",k));
        return String;
    }


    public static Node getstringElements(Document doc, Element element, String k,String p)
    {
        Element node = doc.createElement(k);
        node.appendChild(doc.createTextNode(p));
        return node;
    }


    public static String line;
    public static int res;
    public static void main(String[] args) throws IOException, ParserConfigurationException {

        Scanner cin = new Scanner(System.in);
        System.out.println("Input type of file(txt, json or xml): ");
        String answ = cin.nextLine();

        if (answ.equals("txt"))
        {
            File fin = new File("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.txt");
            FileReader fr = new FileReader(fin);
            BufferedReader reader = new BufferedReader(fr);
            FileWriter out = new FileWriter("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.txt");
            DataSourceDecorator encoded_output = new CompressionDecorator(
                    new EncryptionDecorator(
                            new FileDataSource("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.txt")));

            line = reader.readLine();
            DataSource plain_output = new FileDataSource("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.txt");
            if (line != null) {
                encoded_output.writeData(line);
            }
            out.write("- The result of expression");

            while (line != null) {
             //   System.out.println(line);
                int answer = Calculator.RPN_to_answer((Calculator.expressionToReverse_poland_notation(line.split("\\s"))));
                out.write("\n");
                out.write(answer + " ");
                line = reader.readLine();
            }


            out.write("\n");
            System.out.println("Do you want to encode the expression? (answer yes or no)");
            String str=cin.nextLine();
            if(str.equals("yes")) {
                out.write("- Encoded expression: ");
                out.write("\n");
                out.write(plain_output.readData());
                out.write("\n");
            }
            else{
            out.write("\n");
            }
            out.close();


        } else if (answ.trim().equalsIgnoreCase("xml")) {
            FileInputStream in = new FileInputStream("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.xml");
            StringBuilder sb = new StringBuilder();
            File fin = new File("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.xml");
            FileReader fr = new FileReader(fin);
            BufferedReader reader = new BufferedReader(fr);
            int ch=0;
            while(true){
                ch=in.read();
                if(ch!=-1){
                    sb.append((char)ch);
                }
                else break;
            }
            in.close();
            String xml = sb.toString();
            int start = xml.indexOf("<message>") + "<message>".length();
            int end = xml.indexOf("</message>");
            line = xml.substring(start, end); // в итоге получили сообщение без открывающих и закрывающих скобок "скобок"
            // System.out.println(message);

            FileWriter out = new FileWriter("output.txt");
            DataSourceDecorator encoded_output = new CompressionDecorator(
                    new EncryptionDecorator(
                            new FileDataSource("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.xml")));
            DataSource plain_output = new FileDataSource("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.xml");
            if (line != null) {
                encoded_output.writeData(line);
            }
            out.write("- The result of expression");

            while (line != null) {
                int answer = Calculator.RPN_to_answer((Calculator.expressionToReverse_poland_notation(line.split("\\s"))));
                out.write("\n");
                out.write(answer + " ");
                line = reader.readLine();
            }
            out.write("\n");
            System.out.println("Do you want to encode the expression? (answer yes or no)");
            String str=cin.nextLine();
            if(str.equals("yes")) {
                out.write("- Encoded expression: ");
                out.write("\n");
                out.write(plain_output.readData());
                out.write("\n");
            }
            else{
                out.write("\n");
            }
            out.close();


        } else if (answ.trim().equalsIgnoreCase("json") || answ.trim().equalsIgnoreCase("js"))
        {
            File fin = new File("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.json");
            FileInputStream in = new FileInputStream("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\input.json");
            FileReader fr = new FileReader(fin);
            BufferedReader reader = new BufferedReader(fr);
            DataSource plain_output = new FileDataSource("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.json");
            FileWriter out = new FileWriter("C:\\Users\\lizap\\Desktop\\учьоба\\PAZURA\\Skvoznaya\\src\\output.json");
            StringBuilder sb = new StringBuilder();
            int ch=0;
            while(true){
                ch=in.read();
                if(ch!=-1){
                    sb.append((char)ch);
                }
                else break;
            }
            in.close();
            String json = sb.toString();
            int start = json.indexOf("\"message\":") + "\"message\":".length();
            int end = json.indexOf("}");

            String message = json.substring(start+2, end-3);
            System.out.println(message);
            while (message != null) {
                int answer = Calculator.RPN_to_answer((Calculator.expressionToReverse_poland_notation(message.split("\\s"))));
                out.write("\n");
                out.write(answer + " ");
                message = reader.readLine();
            }


            out.write("\n");
            System.out.println("Do you want to encode the expression? (answer yes or no)");
            String str=cin.nextLine();
            if(str.equals("yes")) {
                out.write("- Encoded expression: ");
                out.write("\n");
                out.write(plain_output.readData());
                out.write("\n");
            }
            else{
                out.write("\n");
            }
            out.close();



        }
        else{
            System.out.println("Ошибка! Возможны только txt, xml, json");
        }


    }
}