package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

/**
 * 培训类
 */
public class Training {
    public static final Integer UNBEGIN = 0;        //还未开始
    public static final Integer FINISH = 1;         //结束
    public static final Integer CANCEL = -1;        //取消

    private Integer id;                                     //培训id
    private String title;                                   //标题
    private String lecturer;                                //讲师
    private String context;                                 //具体内容
    private Date beginDate;                                 //开始时间
    private Integer minute;                                 //时长(分钟)
    private Integer state;                                  //状态：0：还未开始     1：结束     -1：取消
    private String trainingDes;                             //培训部门
    private Set<TrainingNotice> trainingNotices;            //培训通知

    public Training() {
    }

    public String getTrainingDes() {
        return trainingDes;
    }

    public void setTrainingDes(String trainingDes) {
        this.trainingDes = trainingDes;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @JsonIgnore
    public Set<TrainingNotice> getTrainingNotices() {
        return trainingNotices;
    }

    public void setTrainingNotices(Set<TrainingNotice> trainingNotices) {
        this.trainingNotices = trainingNotices;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Training{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", lecturer='").append(lecturer).append('\'');
        sb.append(", context='").append(context).append('\'');
        sb.append(", beginDate=").append(beginDate);
        sb.append(", minute=").append(minute);
        sb.append(", state=").append(state);
        sb.append(", trainingDes='").append(trainingDes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
