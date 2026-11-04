/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author aron
 */
public class main {
    public static void main(String[] args) {
        EntityManager ef = Persistence.createEntityManagerFactory("ConexionJPA").createEntityManager();
        
    }
}
