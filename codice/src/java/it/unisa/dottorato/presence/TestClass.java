/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

import it.unisa.dottorato.phdCourse.Lesson;


/**
 *
 * @author Rembor
 */
public class TestClass {

     private   Presence classList = new Presence();
         private  Lesson classLesson= new Lesson();
    
  public Presence getClassList() {
        return classList;
    }

 
    public String toString() {
        return "TestClass{" + "classList=" + classList.getFkLesson() + ", classLesson=" + classLesson.getName() + '}';
    }

    public Lesson getClassLesson() {
        return classLesson;
    }

    public void setClassList(Presence classList) {
        this.classList = classList;
    }

    public void setClassLesson(Lesson classLesson) {
        this.classLesson = classLesson;
    }
    public static void main(String[] args){
        Presence a=new Presence();
        a.setFkLesson(2);
        a.setFkPhdstudent("gatt");
        Lesson b=new Lesson();
        b.setClassroom("gatto");
        b.setName("mao");
        
        TestClass c=new TestClass();
        c.setClassLesson(b);
        c.setClassList(a);
        System.out.println("ciao"+c);
    }
    }