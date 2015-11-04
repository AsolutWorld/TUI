package main.java.com.asolutworld.Database;
import main.java.com.asolutworld.Authorization.dao.LoginDAO;
import main.java.com.asolutworld.Objects.*;


import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportManager {


    public static void makeCSV(ArrayList<Volunteer> arr){
        try {
            FileWriter writer=new FileWriter(String.valueOf(LoginDAO.getU_ID())+"-volunteers.csv");
            writer.write("Фамилия;Имя;E-Mail;Телефон\n");
            for(Volunteer v:arr){
                writer.append(v.getSname());
                writer.append(';');
                writer.append(v.getFname());
                writer.append(';');
                writer.append(v.getAddress());
                writer.append(';');
                writer.append(v.getPhone());
                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makestsCSV(ArrayList<Stock> arr){
        try {
            FileWriter writer=new FileWriter(String.valueOf(LoginDAO.getU_ID())+"-stocks.csv");
            writer.write("Название;Адрес;Телефон;Время работы\n");
            for(Stock v:arr){
                writer.append(v.getSt_name());
                writer.append(';');
                writer.append(v.getAddress());
                writer.append(';');
                writer.append(v.getPhone());
                writer.append(';');
                writer.append(v.getWork_time());
                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makestrCSV(ArrayList<SRequest> arr,String dest){
        try {
            FileWriter writer=new FileWriter(dest);
            writer.write("Ввод/вывод;Пользователь;Ресурс;Количество;Размерность;Дата\n");
            for(SRequest v:arr){
                writer.append(v.getIn_out());
                writer.append(';');
                writer.append(v.getName());
                writer.append(';');
                writer.append(v.getResource());
                writer.append(';');
                writer.append(String.valueOf(v.getCount()));
                writer.append(';');
                writer.append(v.getType());
                writer.append(';');
                writer.append(v.getDate().toString());
                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makestCSV(ArrayList<SResource> arr,String dest){
        try {
            FileWriter writer=new FileWriter(dest);
            writer.write("Ресурс;Количество;Размерность\n");
            for(SResource v:arr){
                writer.append(v.getResource());
                writer.append(';');
                writer.append(String .valueOf(v.getCount()));
                writer.append(';');
                writer.append(v.getType());

                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makeurCSV(ArrayList<Resource> arr){
        try {
            FileWriter writer=new FileWriter(String.valueOf(LoginDAO.getU_ID())+"-uresources.csv");
            writer.write("Склад;Ресурс;Количество;Размерность\n");
            for(Resource v:arr){
                writer.append(v.getStock_name());
                writer.append(';');
                writer.append(v.getResource());
                writer.append(';');
                writer.append(String.valueOf(v.getCount()));
                writer.append(';');
                writer.append(v.getType());
                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void makereqCSV(ArrayList<SRequest> arr){
        try {
            FileWriter writer=new FileWriter(String.valueOf(LoginDAO.getU_ID())+"-requests.csv");
            writer.write("Пользователь;Адрес;Ресурс;Количество;Размерность;Дата заказа;Активен\n");
            for(SRequest v:arr){
                writer.append(v.getName());
                writer.append(';');
                writer.append(v.getAddress());
                writer.append(';');
                writer.append(v.getResource());
                writer.append(';');
                writer.append(String.valueOf(v.getCount()));
                writer.append(';');
                writer.append(v.getType());
                writer.append(';');
                writer.append(v.getDate().toString());
                writer.append(';');
                writer.append(String.valueOf(v.isActive()));
                writer.append('\n');
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
