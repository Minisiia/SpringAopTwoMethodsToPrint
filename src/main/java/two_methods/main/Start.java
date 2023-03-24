package two_methods.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import two_methods.objects.FileManager;
import two_methods.objects.FileManager2;

/**
 * З прикладу 008_AOP_Interface змінити код: вивести час роботи лише тих методів, які повертають тип Map.
 * Розбити метод друку на 2 види: перший друкує лише Set, другий – лише Map.
 */

public class Start {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        FileManager fileManager = (FileManager) context.getBean("fileManager");
        FileManager2 fileManager2 = (FileManager2) context.getBean("fileManager2");

        System.out.println((char) 27 + "[34m" + "FileManager: " + (char) 27 + "[38m");

        fileManager.getExtensionCount("c:\\Windows\\");
        fileManager.getExtensionList("c:\\Windows\\");

        System.out.println((char) 27 + "[34m" + "FileManager2: " + (char) 27 + "[38m");

        fileManager2.getExtensionCount("c:\\Windows");
        fileManager2.getExtensionList("c:\\Windows\\");

    }
}
