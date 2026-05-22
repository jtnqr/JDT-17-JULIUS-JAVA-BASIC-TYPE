package com.indivaragroup.operator;

public class SwitchStatement {
    public String cuaca = "Malam";

    public void runDemonstration() {
        System.out.println("Cuaca: " + cuaca);

//        regular switch
//        switch (cuaca) {
//            case "Siang":
//                System.out.println("Panas");
//                break;
//            case "Malam":
//                System.out.println("Dingin");
//                break;
//            default:
//                System.out.println("hehe");
//                break;
//        }

//      lambda
//        System.out.print("Suhu: ");
//        switch (cuaca) {
//            case "Siang" -> System.out.println("Panas");
//            case "Malam" -> System.out.println("Dingin");
//            default -> System.out.println("hehe");
//        }

//      yield
        System.out.println("Suhu: " +
                switch (cuaca) {
                    case "Siang" -> "Panas";
                    case "Malam" -> {
                        System.out.println("Cek sensor luar ruangan...");
                        yield "Dingin";
                    }
                    default -> "hehe";
                }
        );
    }
}
