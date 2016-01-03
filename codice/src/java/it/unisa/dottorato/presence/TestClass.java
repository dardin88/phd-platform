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

    public Lesson getClassLesson() {
        return classLesson;
    }

    public void setClassList(Presence classList) {
        this.classList = classList;
    }

    public void setClassLesson(Lesson classLesson) {
        this.classLesson = classLesson;
    }
    
    }