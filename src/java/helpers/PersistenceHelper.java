/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Cu Beo
 */
public class PersistenceHelper {

    public static EntityManager getEntityManager() {
        
        return Persistence.createEntityManagerFactory("esopu").createEntityManager();
    }

    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }
}
