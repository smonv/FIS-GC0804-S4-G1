package beans.admin.statistic;

import entities.Projects;
import helpers.ApplicationHelper;
import helpers.PersistenceHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javafx.beans.property.ListProperty;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.StatisticModel;

@ManagedBean
@RequestScoped
public class IndexStatistic {

    @EJB
    private StatisticModel statisticModel;
    ///////bean variables;
    private long todayOrder;
    private long todayContract;
    private long todayProject;
    private long monthOrder;
    private long monthProject;
    private long monthContract;

    public IndexStatistic() {
    }

    public void init() {
        //////get today statistic
        getTodayStatistic();

        ///////////// get month statistic
        getMonthStatistic();

        //get project start in one week from today
        getProjectsStart();
        //get project end in one week from today
        getProjectsEnd();
    }

    public void getTodayStatistic() {
        Date beginOfDay = beginOfDay();
        Date endOfDay = endOfDay();

        todayOrder = statisticModel.countOrder(beginOfDay, endOfDay);
        todayContract = statisticModel.countContract(beginOfDay, endOfDay);
        todayProject = statisticModel.countProject(beginOfDay, endOfDay);
    }

    public void getMonthStatistic() {
        Date firstDayOfMonth = firstDayOfMonth();
        Date lastDayOfMonth = lastDayOfMonth();
        monthOrder = statisticModel.countOrder(firstDayOfMonth, lastDayOfMonth);
        monthContract = statisticModel.countContract(firstDayOfMonth, lastDayOfMonth);
        monthProject = statisticModel.countProject(firstDayOfMonth, lastDayOfMonth);
    }

    public List<Projects> getProjectsStart() {
        Date today = beginOfDay();
        Date oneWeekAfter = weekAfterToday();
        List<Projects> startProjects = statisticModel.getProjectsStart(today, oneWeekAfter);
        return startProjects;
    }

    public List<Projects> getProjectsEnd() {
        Date today = beginOfDay();
        Date oneWeekAfter = weekAfterToday();
        List<Projects> endProjects = statisticModel.getProjectsEnd(today, oneWeekAfter);
        return endProjects;
    }

    public Date beginOfDay() {
        Calendar cal = new GregorianCalendar();
        cal = setTimeToBegin(cal);
        return cal.getTime();
    }

    public Date endOfDay() {
        Calendar cal = new GregorianCalendar();
        cal = setTimeToEnd(cal);
        return cal.getTime();
    }

    public Date weekAfterToday() {
        Calendar cal = new GregorianCalendar();
        cal = setTimeToEnd(cal);
        cal.add(Calendar.DATE, +7);
        return cal.getTime();
    }

    public Date firstDayOfMonth() {
        Calendar cal = new GregorianCalendar();
        cal = setTimeToBegin(cal);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Date lastDayOfMonth() {
        Calendar cal = new GregorianCalendar();
        cal = setTimeToEnd(cal);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public Calendar setTimeToBegin(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    public Calendar setTimeToEnd(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal;
    }

    public String formatDate(Date date) {
        return ApplicationHelper.formatDate(date, "dd/MM/yyyy");
    }

    ////
    public long getTodayOrder() {
        return todayOrder;
    }

    public void setTodayOrder(long todayOrder) {
        this.todayOrder = todayOrder;
    }

    public long getTodayContract() {
        return todayContract;
    }

    public void setTodayContract(long todayContract) {
        this.todayContract = todayContract;
    }

    public long getTodayProject() {
        return todayProject;
    }

    public void setTodayProject(long todayProject) {
        this.todayProject = todayProject;
    }

    public long getMonthOrder() {
        return monthOrder;
    }

    public void setMonthOrder(long monthOrder) {
        this.monthOrder = monthOrder;
    }

    public long getMonthProject() {
        return monthProject;
    }

    public void setMonthProject(long monthProject) {
        this.monthProject = monthProject;
    }

    public long getMonthContract() {
        return monthContract;
    }

    public void setMonthContract(long monthContract) {
        this.monthContract = monthContract;
    }

}
