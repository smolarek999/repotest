/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.util;

/**
 *
 * @author pawel
 */
public class AssertHelper {

    public static boolean atLeastOneIsNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return true;
            }

        }
        return false;
    }
}
