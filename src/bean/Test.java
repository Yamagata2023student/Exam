package bean;

import java.io.Serializable;

public class Test implements Serializable {

    /**
     * 学生: Student
     */
    private Student student;

    /**
     * クラス番号: String
     */
    private String classNum;

    /**
     * 科目: Subject
     */
    private String subject;

    /**
     * 所属校: School
     */
    private School school;

    /**
     * 学生番号: int
     */
    private int  no;

    /**
     * ポイント: int
     */
    private int point;

    /**
     * 入学年度: int
     */
    private int entYear;

    /**
     * 学生名: string
     */
    private String name;

	/**

    /**
     * ゲッター、セッター
     */

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public  int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public  int getPoint() {
        return point;
    }


    public void setPoint(int point) {
        this.point = point;
    }

///追加した項目
	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}