/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf;

import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestExecution;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author pawel
 */
@ManagedBean
@ViewScoped
public class DashboardBean implements Serializable {

    private DashboardModel model;
List<TestExecution> lastTestsExecutions;
    public DashboardBean() {
        
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("statistics");


        column1.addWidget("lastActions");


        column2.addWidget("testResults");
        column2.addWidget("burndown");


        model.addColumn(column1);
        model.addColumn(column2);
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    @PostConstruct
    public void init() {
        createPieModel();
        createBurnModel();
        lastTestsExecutions = new ArrayList<TestExecution>();
        Test test = new Test();
        test.setName("some Test");
        TestExecution te = new TestExecution();
        te.setState("passed");
        te.setTestidTest(test);
        lastTestsExecutions.add(te);
        
    }
    private PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;

    }

    private void createPieModel() {
        pieModel = new PieChartModel();

        pieModel.set("Passed", 540);
        pieModel.set("Failed", 325);
        pieModel.set("Waiting", 702);
        pieModel.set("Blocked", 421);
    }
    private CartesianChartModel burnModel;

    public CartesianChartModel getBurnModel() {
        return burnModel;
    }

    private void createBurnModel() {
        burnModel = new CartesianChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Test executed");

        series1.set(1, 3);
        series1.set(2, 4);
        series1.set(3, 6);
        series1.set(4, 7);
        series1.set(5, 8);


        burnModel.addSeries(series1);
    }

    public List<TestExecution> getLastTestsExecutions() {
        return lastTestsExecutions;
    }

    public void setLastTestsExecutions(List<TestExecution> lastTestsExecutions) {
        this.lastTestsExecutions = lastTestsExecutions;
    }
    
}
