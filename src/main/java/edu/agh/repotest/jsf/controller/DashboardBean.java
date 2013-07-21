/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.jsf.controller;

import edu.agh.repotest.dao.Condition;
import edu.agh.repotest.dao.Feature;
import edu.agh.repotest.dao.Test;
import edu.agh.repotest.dao.TestAction;
import edu.agh.repotest.dao.TestExecution;
import edu.agh.repotest.dao.TestPlan;
import edu.agh.repotest.dao.TestStatus;
import edu.agh.repotest.dao.TestStatusChange;
import edu.agh.repotest.dao.TestStatusChangeSum;
import edu.agh.repotest.jsf.util.CalendarHelper;
import edu.agh.repotest.session.FeatureFacade;
import edu.agh.repotest.session.TestExecutionFacade;
import edu.agh.repotest.session.TestPlanFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import org.joda.time.LocalDate;
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
    @EJB
    TestExecutionFacade executionFacade;
    @EJB
    TestPlanFacade testPlanFacade;
    @EJB
    FeatureFacade featureFacade;
    List<TestStatusChange> lastTestsExecutions;
    List<TestPlan> openedPlans;

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
        createFeatureCoverageModel();
        openedPlans = testPlanFacade.getOpened();
    }
    private PieChartModel pieModel;

    public PieChartModel getPieModel() {
        return pieModel;

    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        for (TestExecutionFacade.TestStatusCount statusCount : executionFacade.getTestStatusCount()) {
            pieModel.set(statusCount.getStatus().toString(), statusCount.getCount());
        }

    }
    private CartesianChartModel featureCoverageModel;
    private CartesianChartModel burnModel;
    public CartesianChartModel getBurnModel() {
        return burnModel;
    }
    private Long maxCoverage = 0L;
    private void createFeatureCoverageModel() {
        final List<Feature> allFeatures = featureFacade.findAll();
        HashMap<Integer, FeatureFacade.FeatureCount> featureMap = new HashMap<Integer, FeatureFacade.FeatureCount>(allFeatures.size());
        for (Feature feature : allFeatures) {
            featureMap.put(feature.getIdFeature(), new FeatureFacade.FeatureCount(0L, feature.getIdFeature(), feature.getName()));
        }
        for (FeatureFacade.FeatureCount fc : featureFacade.getTestCountByFeature()) {
            System.out.println("FCOUNT"+fc.getIdFeature() + " : " + fc.getCount());
            featureMap.get(fc.getIdFeature()).setCount(fc.getCount());

        }
        
        featureCoverageModel = new CartesianChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Features");

        for (FeatureFacade.FeatureCount fc : featureMap.values()) {
            series1.set(fc.getName(), fc.getCount());
            if( fc.getCount() > maxCoverage ){
                maxCoverage = fc.getCount();
            }
        }
        featureCoverageModel.addSeries(series1);
        
    }

    public CartesianChartModel getFeatureCoverageModel() {
        return featureCoverageModel;
    }

    private void createBurnModel() {
        burnModel = new CartesianChartModel();

        DateMidnight now = new DateMidnight();
        DateMidnight oneWeekAgo = now.minusDays(7);
        Long testExecutionCount = executionFacade.getTestExecutionCount();
        testExecutionCount += executionFacade.getChangeFactorBeforeDate(oneWeekAgo);
        final List<TestStatusChangeSum> testStatusChangeSumForTimePeriod = executionFacade.getTestStatusChangeSumForTimePeriod(oneWeekAgo, now);
        final List<DateMidnight> daysBetween = CalendarHelper.getDaysBetween(oneWeekAgo, now);
        final List<CalendarHelper.TestChangeModel> testBurnDays = CalendarHelper.getTestBurn(daysBetween, testStatusChangeSumForTimePeriod, testExecutionCount);

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Test executed");

        for (CalendarHelper.TestChangeModel burnDay : testBurnDays) {
            series1.set(burnDay.getTimestamp(), burnDay.getTestCount());
        }

        burnModel.addSeries(series1);
    }

    public List<TestStatusChange> getLastTestsExecutions() {
        if (lastTestsExecutions == null) {
            lastTestsExecutions = executionFacade.findNLastStatusChanges(7);
        }
        return lastTestsExecutions;
    }

    public void setLastTestsExecutions(List<TestStatusChange> lastTestsExecutions) {
        this.lastTestsExecutions = lastTestsExecutions;
    }

    public List<TestPlan> getOpenedPlans() {
        return openedPlans;
    }

    public void setOpenedPlans(List<TestPlan> openedPlans) {
        this.openedPlans = openedPlans;
    }

    public Long getMaxCoverage() {
        return maxCoverage;
    }
    
}
