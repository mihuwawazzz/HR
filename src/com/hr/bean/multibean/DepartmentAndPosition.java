package com.hr.bean.multibean;

import com.hr.bean.Department;
import com.hr.bean.Position;

public class DepartmentAndPosition {
    private Department department;
    private Position position;

    public DepartmentAndPosition() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
