/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cms.login;

import cms.login.LoginForm;

/**
 *
 * @author vaibhav
 */
public class MinFrame {
 
    public static void main(String[] args) {
        SpalshScreen screen = new SpalshScreen();
        LoginForm sign = new LoginForm();
        screen.setVisible(true);
        try {
            for (int row = 0; row <=100; row++) {
                Thread.sleep(100);
                screen.loadingnumber.setText(Integer.toString(row)+"%");
                screen.loadingprogress.setValue(row);
                if (row == 100) {
                    
                    screen.setVisible(false);
                    sign.setVisible(true);
                }
            }
        } catch (Exception e) {
        }
    }
}
