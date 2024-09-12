package StructuralDesignPatterns.Decorator;

import StructuralDesignPatterns.Decorator.decorators.*;

public class Demo {
    public static void main(String[] args){
        String salaryRecords = "Name, Salary\nJohn Smith, 100000\nSteven Jobs 912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("out/OutputDemo.txt");
        DataSourceDecorator compressed = new CompressionDecorator(
                        new FileDataSource("out/OutputDemo1.txt"));
        compressed.writeData(salaryRecords);
        DataSource plain1 = new FileDataSource("out/OutputDemo1.txt");
        DataSourceDecorator  encrypted= new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo2.txt"));
        encrypted.writeData(salaryRecords);
        DataSource plain2 = new FileDataSource("out/OutputDemo2.txt");
        System.out.println("- Input -------");
        System.out.println(salaryRecords+"\n");
        System.out.println("- Encoded -----");
        System.out.println(plain.readData()+"\n");
        System.out.println("- Compressed -----");
        System.out.println(plain1.readData()+"\n");
        System.out.println("- Encrypted -----");
        System.out.println(plain2.readData()+"\n");
        System.out.println("- Decoded -----");
        System.out.println(encoded.readData()+"\n");

    }
}
