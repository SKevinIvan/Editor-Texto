/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;

/**
 *
 * @author APOOD9272
 */
public class Project {
    private String Name;
    private ArrayList<Project> childs;

    public Project() {
    }

    public Project(String Name, ArrayList<Project> childs) {
        this.Name = Name;
        this.childs = childs;
    }
    
    
    
    
}
