import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static List<String> readFile (String name) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        String line = "";
        FileInputStream fileInputStream;
        BufferedInputStream reader;
        try {
            fileInputStream = new FileInputStream(name);
            reader = new BufferedInputStream(fileInputStream);
            int i;
            while ((i = reader.read())!= -1)
            {
                if ((char)i == '\n') {
                    lines.add(line.substring(0, line.length() - 1));
                    line = "";
                } else {
                    line += (char)i;
                }
            }
            lines.add(line);
            line = "";
            reader.close();
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public static List<String> tags(List<String> lines){
        List<String> tags = new ArrayList<>();
        Pattern pattern = Pattern.compile("<.+?>");
        Matcher matcher;
        for (String line:lines) {
            matcher = pattern.matcher(line);
            while(matcher.find()){
                tags.add(line.substring(matcher.start(), matcher.end()));
            }
        }
        return tags;
    }

    public static void writeFile(List<String> lines, String name) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(name, true);
            for (String line : lines) {
                fileOutputStream.write(line.getBytes());
                fileOutputStream.write('\n');
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static List<String> sortTags(List<String> tags) {
        List<String> sortedTags = tags;
        String line;
        for (int i = 0; i < sortedTags.size(); i++) {
            for (int j = i + 1; j < sortedTags.size(); j++) {
                if (sortedTags.get(i).length() < sortedTags.get(j).length()) {
                    line = sortedTags.get(i);
                    sortedTags.set(i, sortedTags.get(j));
                    sortedTags.set(j, line);
                }
            }
        }
        return sortedTags;
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        List<String> history = new ArrayList<>();
        List<String> programs = new ArrayList<>();
        List<String> resPrograms = new ArrayList<>();
        List<String> listOfTags = new ArrayList<>();
        int index = 0;
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher;
        lines = readFile("input.txt");
        for (String line:lines) {
            matcher = pattern.matcher(line);
            if (matcher.find()) {
                if (line.substring(0, matcher.start()).equals("Run")) {
                    programs.add(line.substring(matcher.end(), line.length()));
                } else {
                    listOfTags.add(line);
                }
            }
        }
        programs.stream().sorted().forEach(prog -> resPrograms.add(prog));
        writeFile(resPrograms, "output1.txt");
        pattern = Pattern.compile("\\+");
        for (String tag:listOfTags) {
            String[] tabs = pattern.split(tag);

        }

        //listOfTags = sortTags(listOfTags);
        //writeFile(listOfTags, "output2.txt");
    }
}
