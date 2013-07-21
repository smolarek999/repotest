/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.dao;

public class TestStatusHelper {

    public static int getChangeFactorForTransition(TestStatus from, TestStatus to) {
        if (gained(from, to)) {
            return 1;
        } else if (lost(from, to)) {
            return -1;
        } else {
            return 0;
        }

    }

    private static boolean gained(TestStatus from, TestStatus to) {
        return from.getChangeFactor() < 0 && to.getChangeFactor() > 0;
    }

    private static boolean lost(TestStatus from, TestStatus to) {
        return from.getChangeFactor() > 0 && to.getChangeFactor() < 0;
    }
}
