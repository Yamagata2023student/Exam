<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">

    <c:param name="title">
        成績登録システム
</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
<section class="me-4">
<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
<div class="my-2 text-end px-4"></div>
<form method="get">
<div class="row mb-3">
<div class="col-md-3">
<label class="form-label" for="student-f1-select1">入学年度</label>
<select class="form-select" id="student-f1-select1" name="f1">
<option value="0">--------</option>
<c:forEach var="year" items="${ent_year_set}">
<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
</c:forEach>
</select>
</div>
<div class="col-md-3">
<label class="form-label" for="student-f2-select1">クラス</label>
<select class="form-select" id="student-f2-select1" name="f2">
<option value="0">--------</option>
<c:forEach var="classNum" items="${class_num_set}">
<option value="${classNum}" <c:if test="${classNum==f2}">selected</c:if>>${classNum}</option>
</c:forEach>
</select>
</div>
<div class="col-md-3">
<label class="form-label" for="student-f3-select1">科目</label>
<select class="form-select" id="student-f3-select1" name="f3">
<option value="0">--------</option>
<c:forEach var="subject" items="${subject_set}">
<option value="${subject}" <c:if test="${subject==f3}">selected</c:if>>${subject}</option>
</c:forEach>
</select>
</div>
<div class="col-md-3">
<label class="form-label" for="student-f4-select">回数</label>
<select class="form-select" id="student-f4-select" name="f4">
<option value="0">--------</option>
<c:forEach var="no" items="${no_set}">
<option value="${no}" <c:if test="${no==f4}">selected</c:if>>${no}</option>
</c:forEach>
</select>
</div>

                    <div class="col-md-3 d-flex align-items-end">
<input class="btn btn-secondary" type="submit" value="検索" name="end">
</div>
</div>
</form>
</section>


            <c:choose>
<c:when test="${kennsakuitirannSet.size() > 0}">
<table class="table table-hover">
<tr>
<th>入学年度</th>
<th>クラス</th>
<th>科目</th>
<th>学生番号</th>
<th>氏名</th>
<th>点数</th>

                        </tr>
<c:forEach var="test" items="${kennsakuitirannSet}">
<tr>
<td>${test.entYear}</td>
<td>${test.classNum}</td>
<td>${test.subject}</td>
<td>${test.name}</td>
<td>${test.point}</td>
<td class="text-center">
</td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise>
<div>学生情報が存在しませんでした</div>
</c:otherwise>
</c:choose>

    </c:param>
</c:import>