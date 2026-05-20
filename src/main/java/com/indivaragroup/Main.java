package com.indivaragroup;

import com.indivaragroup.activity.dto.MovieDto;
import com.indivaragroup.activity.dto.MoviePlaylistDto;
import com.indivaragroup.activity.logic.MovieService;
import com.indivaragroup.calculation.datatype.dto.ConversionResultDto;
import com.indivaragroup.calculation.datatype.logic.DataTypeConversionService;
import com.indivaragroup.calculation.registration.identity.dto.CalculationRegistrationIdentity;
import com.indivaragroup.calculation.registration.identity.logic.RegistrationService;
import com.indivaragroup.calculation.students.grade.dto.GradeReport;
import com.indivaragroup.calculation.students.grade.dto.StudentRecord;
import com.indivaragroup.calculation.students.grade.logic.ReportService;
import com.indivaragroup.house.floor.dto.Ceramic;
import com.indivaragroup.task.todo.ui.TodoConsoleUi;
import com.indivaragroup.type.array.ArrayDataType;
import com.indivaragroup.type.nonprimitive.NonPrimitiveDataType;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        System.out.println("\n=============FLOOR==============\n");

        Ceramic ceramic = new Ceramic();

        ceramic.setName("Batu");
        if (ceramic.getName().equals("Marmer")) {
            System.out.println("Ini marmer: " + true);
        } else {
            System.out.println("Ini marmer: " + false);
        }

        System.out.println("\n=============MONEY==============\n");

        RegistrationService service = new RegistrationService();

        // Test Case Uang 1
        System.out.println("--- Test Kasus 1: Uang 10000 ---");
        BigDecimal moneyInput1 = new BigDecimal("10000");
        CalculationRegistrationIdentity result1 = service.processRegistration(moneyInput1);
        System.out.println("Hasil Akhir di Objek: " + result1.getMoney());

        System.out.println();

        // Test Case Uang 2
        System.out.println("--- Test Kasus 2: Uang 20000 ---");
        BigDecimal moneyInput2 = new BigDecimal("20000");
        CalculationRegistrationIdentity result2 = service.processRegistration(moneyInput2);
        System.out.println("Hasil Akhir di Objek: " + result2.getMoney());


        System.out.println("\n=============GRADE==============\n");

        ReportService service2 = new ReportService();

        StudentRecord[] dataInput = new StudentRecord[]{
                new StudentRecord("Ucup", 100),
                new StudentRecord("Udin", 65),
                new StudentRecord("Udul", -5)
        };

        GradeReport finalReport = service2.generateReport(dataInput);

        System.out.println("\n------------REPORT--------------\n");

        for (StudentRecord record : finalReport.getRecords()) {
            System.out.println("Siswa: " + record.getName() + " | Nilai: " + record.getGrade());
        }

        System.out.println("\n============D_CONV==============\n");

        DataTypeConversionService service3 = new DataTypeConversionService();

        byte sampleByte = 10;
        double sampleDouble = 9.78;

        ConversionResultDto conversionResultDto = service3.executeConversion(sampleByte, sampleDouble);

        System.out.println("Widening:");
        System.out.println("byte -> int: " + conversionResultDto.getWidenedInt());
        System.out.println("int -> double: " + conversionResultDto.getWidenedDouble());

        System.out.println("Narrowing:");
        System.out.println("double -> int: " + conversionResultDto.getNarrowedInt());
        System.out.println("int -> byte: " + conversionResultDto.getNarrowedByte());

        System.out.println("\n=============N-PRI==============\n");

        NonPrimitiveDataType nonPrimitiveDataType = new NonPrimitiveDataType();

        System.out.println("\n=============ARRAY==============\n");

        ArrayDataType arrayDataType = new ArrayDataType();

        System.out.println("\n=============MOVIE==============\n");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<MovieDto> rawInput = new ArrayList<>();

        try {
            rawInput.add(new MovieDto("SONE-153", sdf.parse("2024-04-19")));
            rawInput.add(new MovieDto("OMHD-015", sdf.parse("2022-02-15")));
            rawInput.add(new MovieDto("SSIS-865", sdf.parse("2023-09-08")));
            rawInput.add(new MovieDto("STARS-368", sdf.parse("2021-05-07")));
            rawInput.add(new MovieDto("HHD-800", sdf.parse("2020-03-04")));
            rawInput.add(new MovieDto("SODS-084", sdf.parse("2026-03-03")));
            rawInput.add(new MovieDto("SDJS-360", sdf.parse("2026-03-03")));
        } catch (java.text.ParseException e) {
            System.err.println("Failed to parse hardcoded date format: " + e.getMessage());
        }

        MovieService movieService = new MovieService();

        MoviePlaylistDto resultPlaylist = movieService.processMovies(rawInput);

        System.out.println("Updated Data:");
        resultPlaylist.getUpdatedList().forEach(movie ->
                System.out.println(movie.getReleaseCode() + " -> " + sdf.format(movie.getReleaseDate()))
        );

        System.out.println("\nNot Updated Data:");
        resultPlaylist.getNotUpdatedList().forEach(movie ->
                System.out.println(movie.getReleaseCode() + " -> " + sdf.format(movie.getReleaseDate()))
        );

        System.out.println("\n=============TO-DO==============\n");

        TodoConsoleUi ui = new TodoConsoleUi();
        ui.start();
    }
}
