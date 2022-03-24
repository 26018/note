package com.moon.note;

import com.moon.note.mapper.PoemDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

@SpringBootTest
class NoteApplicationTests {

//    @Resource
//    PoemDao poemDao;
//
//    @Test
//    public void poemToMysql() throws FileNotFoundException {
//        String path = "C:\\Users\\MoonLight\\Desktop\\poem.txt";
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
//        Object[] objects = bufferedReader.lines().toArray();
////        System.out.println("如何得与凉风约，不共尘沙一并来。".length());
//        for (Object object : objects) {
//            String s = (String) object;
//            String[] a = s.split("\t",2);
////            System.out.println(a[0].length());
//            if(a[0].length() <= 20){
//                poemDao.insert(a[0], a[1]);
//            }
////            System.out.println(Arrays.toString(a));
//        }
//    }

}
