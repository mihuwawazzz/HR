package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

/**
 * ��ѵ��
 */
public class Training {
    public static final Integer UNBEGIN = 0;        //��δ��ʼ
    public static final Integer FINISH = 1;         //����
    public static final Integer CANCEL = -1;        //ȡ��

    private Integer id;                                     //��ѵid
    private String title;                                   //����
    private String lecturer;                                //��ʦ
    private String context;                                 //��������
    private Date beginDate;                                 //��ʼʱ��
    private Integer minute;                                 //ʱ��(����)
    private Integer state;                                  //״̬��0����δ��ʼ     1������     -1��ȡ��
    private String trainingDes;                             //��ѵ����
    private Set<TrainingNotice> trainingNotices;            //��ѵ֪ͨ

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
