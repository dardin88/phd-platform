package it.unisa.dottorato.curriculumcic;

import java.io.Serializable;

/** Classe dell'oggetto curriculumcic; l'oggetto descrive la coppia 
 * curriculum-ciclo
 * e' composto dagli attributi fkProfessor, fkCycle e fkCurriculum che rappresentano
 * rispettivamente l'email del professore coordinatore del curriculum-ciclo, il 
 * numero di ciclo e il nome del curriculum
 *
 * @author tommaso Minichiello
 */
public class Curriculumcic implements Serializable{
    
    private String fkProfessor;
    private int fkCycle;
    private String fkCurriculum;

     /** Metodo della classe incaricato a ritornare l'email di un eventuale
       * coordinatore del curriculum-ciclo
       **  @return il coordinatore del ciclo-curriculum
    */
    public String getfkProfessor() {
        return fkProfessor;
    }

     /** Metodo della classe incaricato a settare il coordinatore del 
      * curriculum-ciclo
    **   @param fkProfessor il nuovo coordinatore del curriculum-ciclo
    */
    public void setfkProfessor(String fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

     /** Metodo della classe incaricato a ritornare il numero del ciclo
      * del curriculum-ciclo
    **  @return numero del ciclo
    */
    public int getfkCycle() {
        return fkCycle;
    }

     /** Metodo della classe incaricato a settare il ciclo del curriculum-ciclo
    **   @param fkCycle il nuovo ciclo del curriculum-ciclo
    */
    public void setfkCycle(int fkCycle) {
        this.fkCycle = fkCycle;
    }

     /** Metodo della classe incaricato a ritornare il nome del curriculum
      * del curriculum-ciclo
    **  @return nome del curriculum
    */
    public String getfkCurriculum() {
        return fkCurriculum;
    }
    

      /** Metodo della classe incaricato a settare il curriculum 
       * del curriculum-ciclo
    **   @param fkCurriculum il nuovo curriculum del curriculum-ciclo
    */
    public void setfkCurriculum(String fkCurriculum) {
        this.fkCurriculum = fkCurriculum;
    }
    
}
