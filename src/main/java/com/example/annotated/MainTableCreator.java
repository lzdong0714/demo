package com.example.annotated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description 注解解释器的运行事例
 * @createTime 2019年08月14日 15:32:00
 */
public class MainTableCreator {

    Logger logger = LoggerFactory.getLogger(getClass());

    public static String createTableSql(String className) throws ClassNotFoundException {

        Class<?> cl = Class.forName(className);

        DBTable dbTable = cl.getAnnotation(DBTable.class);

        if(dbTable == null){
            System.out.println("No DBTable annotations in class" + className);
            return null;
        }

        String tableName = dbTable.name();
        if(tableName.length() < 1){
            tableName = cl.getName().toUpperCase();
        }

        List<String> columnDefs = new ArrayList<String>();

        for (Field field : cl.getDeclaredFields()){
            String columnName = null;

            Annotation[] anns = field.getAnnotations();

            if(anns.length < 1){
                continue;
            }

            if(anns[0] instanceof SQLInteger){
                SQLInteger sInt = (SQLInteger) anns[0];

                if(sInt.name().length() < 1){
                    columnName = field.getName();
                }else {
                    columnName = sInt.name();
                }

                columnDefs.add(columnName + " INT " + getConstraints(sInt.constraints()));
            }

            if(anns[0] instanceof  SQLString){
                SQLString sqlString = (SQLString) anns[0];

                if(sqlString.name().length() < 1){
                    columnName = field.getName();
                }else {
                    columnName = sqlString.name();
                }

                columnDefs.add(columnName + " VARCHAR(" + sqlString.value() + ")"
                 + getConstraints(sqlString.contraints()));
            }

        }

        StringBuilder createCommand = new StringBuilder(
                "CREATE TABLE" + tableName + "("
        );

        for (String columnDef : columnDefs){
            createCommand.append("\n " + columnDef + ",");
        }

        String tableCreate = createCommand.substring(
                0, createCommand.length() - 1) +");";

        return tableCreate;
    }


    private static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull()){
            constraints += "NOT NULL";
        }

        if(con.primaryKey()){
            constraints += "PRIMARY KEY";
        }

        if(con.unique()){
            constraints += "UNIQUE";
        }
        return constraints;
    }

    public static void main(String[] args) {
        String[] arg = {"com.example.annotated.Member"};

        for(String className : arg){
            try {
                System.out.println("Table Create SQL for "+ className +
                        "is: \n" + createTableSql(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
