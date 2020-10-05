package ru.geekbrains.lesson2;

import java.util.Arrays;

//      1. Есть строка вида: "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0"; (другими словами матрица 4x4)
//        10 3 1 2
//        2 3 2 2
//        5 6 7 1
//        300 3 1 0
//        Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив
//        типа String[][];
//      2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и
//      вернуть результат;
//      3. Ваши методы должны бросить исключения в случаях:
//        Если размер матрицы, полученной из строки, не равен 4x4;
//        Если в одной из ячеек полученной матрицы не число; (например символ или слово)
//      4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат
//      расчета.
//      5. * Написать собственные классы исключений для каждого из случаев
public class Main {
    private static final class RowMismatchExeption extends RuntimeException{
        public RowMismatchExeption(String message) {
            super(message);
        }
    }
    private static final class ColumnMismatchExeption extends RuntimeException{
        public ColumnMismatchExeption(String message) {
            super(message);
        }
    }
    private static final class NumberIsNotNumberExeption extends RuntimeException{
        public NumberIsNotNumberExeption(String message) {
            super(message);
        }
    }



    private static final String CORRECT_STRING = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";

    private static final int MATRIX_ROWS = 4;
    private static final int MATRIX_COLS = 4;

    private static String[][] stringToMatrix(String value){
        String[] rows = value.split("\n");
        if (rows.length != MATRIX_ROWS){
            throw new RowMismatchExeption(rows.length + ":\n"+ value);
        }
        String[][] result = new String[MATRIX_ROWS][];
        for(int i = 0; i < result.length;i++) {
            result[i] = rows[i].split(" ");
            if (result[i].length != MATRIX_COLS){
                throw new ColumnMismatchExeption(result[i].length + ":\n" + value);
            }
        }
        return result;
    }
    private static float calcMatrix(String[][] matrix){
        int result = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                try{
                    result += Integer.parseInt(matrix[i][j]);
                }catch (NumberFormatException e){
                    throw new NumberIsNotNumberExeption(matrix[i][j]);
                }
            }
        }
        return result / 2f;
    }
    public static void main(String[] args){
        try {
            final String[][] matrix = stringToMatrix(CORRECT_STRING);
            System.out.println(Arrays.deepToString(matrix));
            System.out.println("Half sum = "+ calcMatrix(matrix));
        }catch (NumberIsNotNumberExeption e){
            System.out.println("A NumberFormatExeption is thrown: "+ e.getMessage());
        }catch (RowMismatchExeption | ColumnMismatchExeption e){
            System.out.println("A RuntimeExeption successar is thrown: " + e.getMessage());
        }
    }
}
