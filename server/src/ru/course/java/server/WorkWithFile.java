package ru.course.java.server;

import ru.course.java.client.User;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WorkWithFile {


    private static final String nameOfFile = "users.txt" ;

    public boolean serializator(User user) throws IOException {

        boolean flag = false;

        File file = new File(nameOfFile);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file,true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            flag = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        oos.flush();
        oos.close();
        fos.flush();
        fos.close();
        return flag;

    }


    public void deSerializator() throws  IOException, ClassNotFoundException {

        File file = new File(nameOfFile);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = null;
        User user = null;

        try {

            for(;;) {
                ois = new ObjectInputStream(fis);
                user = (User)ois.readObject();
                System.out.println(user.toString());

            }


        } catch (EOFException e)
        {
            //System.out.println("End of file reached.");
        }

        ois.close();
        fis.close();



    }

    public boolean searchInFile(String str) throws IOException, ClassNotFoundException {

        boolean flag = false;

        File file = new File(nameOfFile);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = null;
        User user = null;

        try {

            for(;;) {
                ois = new ObjectInputStream(fis);
                user = (User)ois.readObject();
                if(user.getLogin().equals(str)) flag = true;

            }


        } catch (EOFException e)
        {
            // System.out.println("End of file reached.");
        }

        ois.close();
        fis.close();

        return flag;


    }

}

