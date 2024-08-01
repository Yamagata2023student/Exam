package bean;

import java.io.Serializable;

public class Teacher extends User implements Serializable {
	/**
	 * 教員ID:String
	 */
	private String id;

	/**
	 * パスワード:String
	 */
	private String password;

	/**
	 * 教員名:String
	 */
	private String name;

	/**
	 * 所属校:School
	 */
	private School school;

	/**
	 * ゲッター、セッター
	 */

    /**
     * クラス番号: String
     */
    private String classNum;

    ///??????????????????
    /**
     * 学生: Student
     */
    private Student student;


    /**
     * 科目: Subject
     */
    private String subject;



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

    /**
     * ゲッター、セッター
     */

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

  ///追加した項目
  	public int getEntYear() {
  		return entYear;
  	}

  	public void setEntYear(int entYear) {
  		this.entYear = entYear;
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }


}