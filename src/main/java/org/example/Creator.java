package org.example;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class Creator {

    private static final int IN_OPERAND_SIZE = 1000;

    public static void main(String[] args) {
        // Generate test data
        Long[] testData = generateTestData(2001); // Generate 2500 Long values

        // Call the createAllInExpression method
        List<Long[]> result = createAllInExpression("testField", testData);

        // Print the results
        printResults(result);
    }


    public static List<Long[]> createAllInExpression(String field, Long[] list) {
        // Считаем пачки 234
        int batchNumber = (list.length + (IN_OPERAND_SIZE-1)) / IN_OPERAND_SIZE;

List<Long[]> result = new ArrayList<>();


        for (int i = 0; i < batchNumber; i++) {
            int start = i * IN_OPERAND_SIZE;
            int end = Math.min(start + IN_OPERAND_SIZE, list.length);

            // Создаем subArray из текущей пачки
            Long[] subArray = ArrayUtils.subarray(list, start, end);

            // Добавляем subArray в disjunction
            result.add( subArray);
        }

        return result;
    }


    private static Long[] generateTestData(int size) {
        Long[] data = new Long[size];
        for (int i = 0; i < size; i++) {
            data[i] = (long) (i + 1); // Generate Long values from 1 to size
        }
        return data;
    }

    private static void printResults(List<Long[]> result) {
        for (Long[] array : result) {
            System.out.println("Batch: " + java.util.Arrays.toString(array));
        }
    }
}
