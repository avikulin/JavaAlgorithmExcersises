package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionF {
    public static Integer getMaxPerimeter(Integer[] sections){
        Arrays.sort(sections);
        int maxLengthSidePointer = sections.length - 1;
        int midLengthSidePointer = maxLengthSidePointer - 1;
        int minLengthSidePointer = midLengthSidePointer - 1;

        while (maxLengthSidePointer!=2){
            while (midLengthSidePointer != 1){
                int sideA = sections[maxLengthSidePointer];
                int sideB = sections[midLengthSidePointer];
                int sideC = sections[minLengthSidePointer];
                if ((sideA < sideB + sideC) && (sideA !=0) && (sideB!=0)&&(sideC!=0)) return sideA + sideB + sideC;
                midLengthSidePointer--;
                minLengthSidePointer = midLengthSidePointer -1;
            }
            maxLengthSidePointer--;
            midLengthSidePointer = maxLengthSidePointer - 1;
            minLengthSidePointer = midLengthSidePointer - 1;
        }

        if (sections[maxLengthSidePointer] < sections[midLengthSidePointer] + sections[minLengthSidePointer])
            return sections[maxLengthSidePointer] + sections[midLengthSidePointer] + sections[minLengthSidePointer];

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfSections = Integer.parseInt(reader.readLine());
        StringTokenizer sectionsTokens = new StringTokenizer(reader.readLine());
        Integer[] sectionsLength = new Integer[numberOfSections];
        for (int i=0; i < numberOfSections; i++)
            sectionsLength[i] = Integer.parseInt(sectionsTokens.nextToken());

        System.out.println(getMaxPerimeter(sectionsLength));
    }
}
